package com.vistoria.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vistoria.app.R

class AmbienteAdapter(private val ambientes: List<String>?) :
    RecyclerView.Adapter<AmbienteAdapter.AmbienteViewHolder>() {

    class AmbienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNomeAmbiente: TextView? = itemView.findViewById(R.id.textNomeAmbiente)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbienteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ambiente, parent, false)
        return AmbienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: AmbienteViewHolder, position: Int) {
        val ambiente = ambientes?.getOrNull(position)
        holder.textNomeAmbiente?.text = ambiente ?: "Ambiente não disponível"
    }

    override fun getItemCount(): Int = ambientes?.size ?: 0
}
