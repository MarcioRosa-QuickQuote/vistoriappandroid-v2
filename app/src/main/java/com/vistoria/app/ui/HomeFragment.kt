package com.vistoria.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.vistoria.app.adapter.AmbienteAdapter
import com.vistoria.app.databinding.FragmentHomeBinding
import com.vistoria.app.util.AssetLoader

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ambientes = AssetLoader.loadAmbientes(requireContext())
        val adapter = AmbienteAdapter(ambientes) { ambiente ->
            Toast.makeText(requireContext(), "Clicou em: ${ambiente.nome}", Toast.LENGTH_SHORT).show()
            // Aqui depois podemos abrir a tela do ambiente específico!
        }

        binding.recyclerViewAmbientes.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewAmbientes.adapter = adapter

        // Botão Finalizar Vistoria
        binding.buttonFinalizarVistoria.setOnClickListener {
            Toast.makeText(requireContext(), "Finalizando Vistoria...", Toast.LENGTH_SHORT).show()
            // TODO: Implementar a lógica real de finalização
        }

        // Botão Nova Vistoria
        binding.buttonNovaVistoria.setOnClickListener {
            Toast.makeText(requireContext(), "Nova Vistoria", Toast.LENGTH_SHORT).show()
            // TODO: Podemos abrir uma nova tela de criação de vistoria
        }

        // Botão Histórico
        binding.buttonHistorico.setOnClickListener {
            Toast.makeText(requireContext(), "Histórico de Vistorias", Toast.LENGTH_SHORT).show()
            // TODO: Implementar tela de histórico
        }

        // Botão Configurações
        binding.buttonConfiguracoes.setOnClickListener {
            Toast.makeText(requireContext(), "Abrindo Configurações...", Toast.LENGTH_SHORT).show()
            // TODO: Implementar tela de configurações
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
