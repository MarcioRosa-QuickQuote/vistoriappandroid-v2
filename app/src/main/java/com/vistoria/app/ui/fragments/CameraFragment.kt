package com.vistoria.app.ui.fragments

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vistoria.app.R
import com.vistoria.app.adapters.PhotoThumbnailAdapter
import com.vistoria.app.databinding.FragmentCameraBinding
import com.vistoria.app.models.Photo
import com.vistoria.app.models.Room
import com.vistoria.app.utils.ImageUtils
import com.vistoria.app.utils.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class CameraFragment : Fragment(), PhotoThumbnailAdapter.PhotoThumbnailClickListener {
    
    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService
    private var currentRoom: Room? = null
    private var roomId: String? = null
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var thumbnailAdapter: PhotoThumbnailAdapter
    private var flashMode = ImageCapture.FLASH_MODE_OFF
    private var isTakingPhoto = false
    
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                startCamera()
            } else {
                Toast.makeText(requireContext(), "Permissões de câmera são necessárias", Toast.LENGTH_LONG).show()
                findNavController().navigateUp()
            }
        }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inicializar executor da câmera
        cameraExecutor = Executors.newSingleThreadExecutor()
        
        // Obter ID da sala dos argumentos
        roomId = arguments?.getString("roomId")
        
        // Inicializar preferências e carregar dados da sala
        preferenceManager = PreferenceManager(requireContext())
        loadRoomData()
        
        // Configurar botões
        setupButtons()
        
        // Configurar adaptador de miniaturas
        setupThumbnailAdapter()
        
        // Verificar permissões de câmera
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestCameraPermissions()
        }
    }
    
    private fun setupButtons() {
        // Botão para capturar foto
        binding.buttonTakePhoto.setOnClickListener {
            if (!isTakingPhoto) {
                takePhoto()
            }
        }
        
        // Botão para finalizar e voltar
        binding.buttonFinish.setOnClickListener {
            findNavController().navigateUp()
        }
        
        // Alternar flash
        binding.buttonFlash.setOnClickListener {
            toggleFlash()
        }
    }
    
    private fun setupThumbnailAdapter() {
        thumbnailAdapter = PhotoThumbnailAdapter(currentRoom?.photos ?: ArrayList(), this)
        binding.recyclerViewPhotos.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = thumbnailAdapter
        }
        
        // Atualizar contador de fotos
        updatePhotoCountText()
    }
    
    private fun loadRoomData() {
        val inspection = preferenceManager.getCurrentInspection()
        
        if (roomId != null) {
            // Encontrar a sala pelo ID
            currentRoom = inspection.rooms.find { it.id == roomId }
            
            if (currentRoom != null) {
                // Atualizar o nome da sala na UI
                binding.textViewRoomName.text = currentRoom?.name ?: ""
                
                // Atualizar o contador de fotos
                updatePhotoCountText()
            } else {
                Toast.makeText(requireContext(), "Sala não encontrada", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        } else {
            Toast.makeText(requireContext(), "ID da sala não fornecido", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
    
    private fun updatePhotoCountText() {
        val photoCount = currentRoom?.photos?.size ?: 0
        binding.textViewPhotoCount.text = "$photoCount ${if (photoCount == 1) "foto" else "fotos"}"
    }
    
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }
    
    private fun requestCameraPermissions() {
        requestPermissionLauncher.launch(REQUIRED_PERMISSIONS)
    }
    
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        
        cameraProviderFuture.addListener({
            try {
                // Obter provedor de câmera
                val cameraProvider = cameraProviderFuture.get()
                
                // Configurar preview
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(binding.previewView.surfaceProvider)
                    }
                
                // Configurar captura de imagem
                imageCapture = ImageCapture.Builder()
                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                    .setFlashMode(flashMode)
                    .build()
                
                // Selecionar câmera traseira
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                
                // Vincular casos de uso à câmera
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
                
            } catch (e: Exception) {
                Log.e(TAG, "Falha ao iniciar a câmera: ${e.message}", e)
                Toast.makeText(requireContext(), "Erro ao iniciar a câmera", Toast.LENGTH_SHORT).show()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }
    
    private fun toggleFlash() {
        flashMode = if (flashMode == ImageCapture.FLASH_MODE_OFF) {
            binding.buttonFlash.setImageResource(R.drawable.ic_flash_on)
            ImageCapture.FLASH_MODE_ON
        } else {
            binding.buttonFlash.setImageResource(R.drawable.ic_flash_off)
            ImageCapture.FLASH_MODE_OFF
        }
        
        // Reiniciar a câmera para aplicar a alteração
        startCamera()
    }
    
    private fun takePhoto() {
        // Verificar se a captura de imagem está inicializada
        val imageCapture = imageCapture ?: return
        
        // Prevenir múltiplos cliques
        isTakingPhoto = true
        
        // Mostrar indicador de progresso
        binding.progressBar.visibility = View.VISIBLE
        
        try {
            // Criar arquivo temporário para a foto
            val photoFile = ImageUtils.createImageFile(requireContext())
            
            // Criar opções de saída para o arquivo
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
            
            // Capturar a foto
            imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(requireContext()),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        processImageInBackground(photoFile)
                    }
                    
                    override fun onError(exception: ImageCaptureException) {
                        Log.e(TAG, "Erro ao capturar foto: ${exception.message}", exception)
                        Toast.makeText(
                            requireContext(),
                            "Erro ao capturar foto: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        isTakingPhoto = false
                        binding.progressBar.visibility = View.GONE
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao configurar captura de foto: ${e.message}", e)
            Toast.makeText(
                requireContext(),
                "Erro ao configurar captura de foto",
                Toast.LENGTH_SHORT
            ).show()
            isTakingPhoto = false
            binding.progressBar.visibility = View.GONE
        }
    }
    
    private fun processImageInBackground(photoFile: java.io.File) {
        lifecycleScope.launch {
            try {
                // Processar a imagem em segundo plano
                val optimizedFile = withContext(Dispatchers.IO) {
                    ImageUtils.optimizeImage(photoFile)
                }
                
                // Criar thumbnail
                val thumbnailFile = withContext(Dispatchers.IO) {
                    ImageUtils.createThumbnail(optimizedFile ?: photoFile)
                }
                
                // Criar objeto de foto
                val photo = Photo(
                    id = UUID.randomUUID().toString(),
                    url = (optimizedFile ?: photoFile).absolutePath,
                    thumbnailUrl = thumbnailFile?.absolutePath ?: "",
                    timestamp = Date()
                )
                
                // Adicionar foto à sala atual
                currentRoom?.let { room ->
                    room.photos.add(photo)
                    
                    // Atualizar a inspeção no gerenciador de preferências
                    val inspection = preferenceManager.getCurrentInspection()
                    val roomIndex = inspection.rooms.indexOfFirst { it.id == room.id }
                    
                    if (roomIndex >= 0) {
                        inspection.rooms[roomIndex] = room
                        preferenceManager.saveCurrentInspection(inspection)
                    }
                    
                    // Atualizar UI
                    updatePhotoCountText()
                    thumbnailAdapter.updatePhotos(room.photos)
                    binding.recyclerViewPhotos.scrollToPosition(room.photos.size - 1)
                }
                
                // Limpar status de captura
                isTakingPhoto = false
                binding.progressBar.visibility = View.GONE
                
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao processar imagem: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao processar imagem",
                        Toast.LENGTH_SHORT
                    ).show()
                    isTakingPhoto = false
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
    
    override fun onThumbnailClick(photo: Photo, position: Int) {
        // Implementação futura: visualizar foto em tela cheia
    }
    
    override fun onThumbnailDeleteClick(photo: Photo, position: Int) {
        currentRoom?.let { room ->
            // Remover a foto
            room.photos.removeAt(position)
            
            // Atualizar a inspeção
            val inspection = preferenceManager.getCurrentInspection()
            val roomIndex = inspection.rooms.indexOfFirst { it.id == room.id }
            
            if (roomIndex >= 0) {
                inspection.rooms[roomIndex] = room
                preferenceManager.saveCurrentInspection(inspection)
            }
            
            // Atualizar UI
            thumbnailAdapter.updatePhotos(room.photos)
            updatePhotoCountText()
            
            Toast.makeText(requireContext(), "Foto removida", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
        _binding = null
    }
    
    companion object {
        private const val TAG = "CameraFragment"
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }
}