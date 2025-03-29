package com.vistoria.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vistoria.app.R
import com.vistoria.app.databinding.FragmentHomeBinding
import com.vistoria.app.utils.PreferenceManager

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        preferenceManager = PreferenceManager(requireContext())
        
        // Configurar botões
        binding.buttonNewInspection.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_novaVistoriaFragment)
        }
        
        binding.buttonHistory.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_historicoFragment)
        }
        
        binding.buttonContinueInspection.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_novaVistoriaFragment)
        }
        
        // Verificar se há uma vistoria em andamento
        checkCurrentInspection()
    }
    
    override fun onResume() {
        super.onResume()
        // Atualizar informações na tela ao retornar ao fragmento
        checkCurrentInspection()
    }
    
    private fun checkCurrentInspection() {
        val currentInspection = preferenceManager.getCurrentInspection()
        
        // Verificar se há dados da vistoria
        if (currentInspection.clientName.isNotEmpty() || 
            currentInspection.rooms.any { it.photos.isNotEmpty() || it.description.isNotEmpty() }) {
            
            // Mostrar card com detalhes da vistoria atual
            binding.textViewCurrentInspection.visibility = View.VISIBLE
            binding.cardCurrentInspection.visibility = View.VISIBLE
            
            // Preencher dados
            binding.textViewClientName.text = if (currentInspection.clientName.isNotEmpty()) {
                "Cliente: ${currentInspection.clientName}"
            } else {
                "Cliente: Não informado"
            }
            
            // Calcular status de ambientes
            val completedRooms = currentInspection.rooms.count { it.photos.isNotEmpty() }
            val totalRooms = currentInspection.rooms.size
            
            binding.textViewRoomStatus.text = "$completedRooms de $totalRooms ambientes fotografados"
            
        } else {
            // Esconder informações da vistoria atual
            binding.textViewCurrentInspection.visibility = View.GONE
            binding.cardCurrentInspection.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}