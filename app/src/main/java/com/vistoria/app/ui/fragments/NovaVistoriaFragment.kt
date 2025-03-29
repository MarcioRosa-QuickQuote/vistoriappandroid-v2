package com.vistoria.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vistoria.app.R
import com.vistoria.app.adapters.RoomAdapter
import com.vistoria.app.databinding.FragmentNovaVistoriaBinding
import com.vistoria.app.models.Inspection
import com.vistoria.app.models.Room
import com.vistoria.app.utils.PreferenceManager
import com.vistoria.app.utils.PdfGenerator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class NovaVistoriaFragment : Fragment(), RoomAdapter.RoomClickListener {

    private var _binding: FragmentNovaVistoriaBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var inspection: Inspection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNovaVistoriaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        preferenceManager = PreferenceManager(requireContext())
        
        // Inicializar inspeção
        inspection = preferenceManager.getCurrentInspection()
        
        // Configurar RecyclerView
        setupRecyclerView()
        
        // Preencher dados da tela, se houver
        populateFields()
        
        // Configurar ações dos botões
        binding.buttonFinishInspection.setOnClickListener {
            if (validateAndSaveInspection()) {
                generateReport()
            }
        }
    }
    
    private fun setupRecyclerView() {
        roomAdapter = RoomAdapter(inspection.rooms, this)
        binding.recyclerViewRooms.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = roomAdapter
        }
    }
    
    private fun populateFields() {
        // Preencher campo do nome do cliente
        binding.editTextClientName.setText(inspection.clientName)
    }
    
    private fun validateAndSaveInspection(): Boolean {
        // Obter dados do formulário
        val clientName = binding.editTextClientName.text.toString().trim()
        
        // Validar nome do cliente
        if (clientName.isEmpty()) {
            binding.textInputLayoutClientName.error = getString(R.string.error_required_field)
            return false
        } else {
            binding.textInputLayoutClientName.error = null
        }
        
        // Atualizar dados da inspeção
        inspection.clientName = clientName
        
        // Salvar inspeção
        preferenceManager.saveCurrentInspection(inspection)
        return true
    }
    
    private fun generateReport() {
        // Mostrar mensagem de progresso
        Toast.makeText(requireContext(), "Gerando relatório...", Toast.LENGTH_SHORT).show()
        
        // Gerar PDF em uma coroutine para não bloquear a UI
        lifecycleScope.launch {
            val pdfGenerator = PdfGenerator(requireContext())
            
            val pdfFile = withContext(Dispatchers.IO) {
                pdfGenerator.generateInspectionPdf(inspection, preferenceManager)
            }
            
            if (pdfFile != null) {
                // Salvar na história de inspeções
                val historyInspection = inspection.copy(
                    id = System.currentTimeMillis(),
                    createdAt = Date()
                )
                preferenceManager.saveInspectionToHistory(historyInspection)
                
                // Limpar inspeção atual
                preferenceManager.clearCurrentInspection()
                
                // Mostrar diálogo de conclusão
                withContext(Dispatchers.Main) {
                    showCompletionDialog(pdfFile.absolutePath)
                }
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao gerar o relatório. Tente novamente.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    
    private fun showCompletionDialog(pdfPath: String) {
        // Criar bundle com argumentos
        val bundle = Bundle().apply {
            putString("pdfPath", pdfPath)
        }
        
        // Navegar para o fragmento de conclusão
        findNavController().navigate(R.id.action_novaVistoriaFragment_to_inspectionCompletedFragment, bundle)
    }
    
    // Implementação da interface RoomClickListener
    override fun onTakePhotoClick(room: Room, position: Int) {
        // Criar bundle com argumentos
        val bundle = Bundle().apply {
            putString("roomId", room.id)
        }
        
        // Navegar para a tela da câmera
        findNavController().navigate(R.id.action_novaVistoriaFragment_to_cameraFragment, bundle)
    }
    
    override fun onAddDescriptionClick(room: Room, position: Int) {
        // Criar bundle com argumentos
        val bundle = Bundle().apply {
            putString("roomId", room.id)
        }
        
        // Navegar para o fragmento de descrição
        findNavController().navigate(R.id.action_novaVistoriaFragment_to_roomDescriptionFragment, bundle)
    }
    
    override fun onRemovePhotoClick(roomId: String, photoId: String) {
        // Encontrar a sala
        val roomIndex = inspection.rooms.indexOfFirst { it.id == roomId }
        if (roomIndex >= 0) {
            // Encontrar e remover a foto
            val photoIndex = inspection.rooms[roomIndex].photos.indexOfFirst { it.id == photoId }
            if (photoIndex >= 0) {
                inspection.rooms[roomIndex].photos.removeAt(photoIndex)
                
                // Atualizar a UI
                roomAdapter.notifyItemChanged(roomIndex)
                
                // Salvar alterações
                preferenceManager.saveCurrentInspection(inspection)
                
                Toast.makeText(requireContext(), "Foto removida", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Salvar quaisquer mudanças pendentes antes de destruir a view
        if (this::inspection.isInitialized) {
            val clientName = binding.editTextClientName.text.toString().trim()
            inspection.clientName = clientName
            preferenceManager.saveCurrentInspection(inspection)
        }
        
        _binding = null
    }
}