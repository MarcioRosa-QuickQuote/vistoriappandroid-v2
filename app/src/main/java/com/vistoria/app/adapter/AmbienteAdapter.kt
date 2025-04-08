package com.vistoria.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vistoria.app.databinding.ItemAmbienteCardBinding
import com.vistoria.app.model.Ambiente

class AmbienteAdapter(
    private val ambientes: List<Ambiente>,
    private val onItemClick: (Ambiente) -> Unit
) : RecyclerView.Adapter<AmbienteAdapter.AmbienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbienteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAmbienteCardBinding.inflate(inflater, parent, false)
        return AmbienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AmbienteViewHolder, position: Int) {
        val ambiente = ambientes[position]
        holder.bind(ambiente)
    }

    override fun getItemCount(): Int = ambientes.size

    inner class AmbienteViewHolder(private val binding: ItemAmbienteCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ambiente: Ambiente) {
            val context = binding.root.context
            val imageResId = context.resources.getIdentifier(
                ambiente.imagemPath,
                "drawable",
                context.packageName
            )
            binding.imageAmbiente.setImageResource(imageResId)

            // ✅ Aqui está a mudança correta!
            binding.textNomeAmbiente.text = ambiente.nome

            binding.textFotoInfo.text = if (ambiente.qtdFotos > 0) {
                "${ambiente.qtdFotos} fotos"
            } else {
                "Sem fotos"
            }

            binding.root.setOnClickListener {
                onItemClick(ambiente)
            }
        }
    }
}
