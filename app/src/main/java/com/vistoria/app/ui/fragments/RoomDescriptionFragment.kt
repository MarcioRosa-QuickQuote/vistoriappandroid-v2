package com.vistoria.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vistoria.app.R
import com.vistoria.app.databinding.FragmentRoomDescriptionBinding
import com.vistoria.app.utils.PreferenceManager

class RoomDescriptionFragment : Fragment() {

    private var _binding: FragmentRoomDescriptionBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var preferenceManager: PreferenceManager
    private var roomId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inicializar o gerenciador de preferências
        preferenceManager = PreferenceManager(requireContext())
        
        // Obter o ID da sala dos argumentos
        roomId = arguments?.getString("roomId")
        
        // Carregar dados da sala
        loadRoomData()
        
        // Configurar botão de salvar
        binding.buttonSaveDescription.setOnClickListener {
            saveDescription()
        }
    }
    
    private fun loadRoomData() {
        val inspection = preferenceManager.getCurrentInspection()
        
        if (roomId != null) {
            // Encontrar a sala pelo ID
            val room = inspection.rooms.find { it.id == roomId }
            
            if (room != null) {
                // Preencher os campos da tela
                binding.textViewRoomName.text = room.name
                binding.editTextDescription.setText(room.description)
            } else {
                Toast.makeText(requireContext(), "Sala não encontrada", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        } else {
            Toast.makeText(requireContext(), "ID da sala não fornecido", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }
    
    private fun saveDescription() {
        val description = binding.editTextDescription.text.toString().trim()
        
        // Atualizar a descrição da sala
        val inspection = preferenceManager.getCurrentInspection()
        
        if (roomId != null) {
            val roomIndex = inspection.rooms.indexOfFirst { it.id == roomId }
            
            if (roomIndex >= 0) {
                // Atualizar a descrição
                inspection.rooms[roomIndex].description = description
                
                // Salvar a inspeção atualizada
                preferenceManager.saveCurrentInspection(inspection)
                
                // Mostrar mensagem de sucesso
                Toast.makeText(requireContext(), "Descrição salva", Toast.LENGTH_SHORT).show()
                
                // Voltar para a tela anterior
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Sala não encontrada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}