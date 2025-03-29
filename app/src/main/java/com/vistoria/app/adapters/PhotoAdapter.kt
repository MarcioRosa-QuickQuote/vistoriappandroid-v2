package com.vistoria.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vistoria.app.R
import com.vistoria.app.models.Photo
import java.io.File

/**
 * Adaptador para exibir miniaturas de fotos em um RecyclerView
 */
class PhotoAdapter(
    private var photos: List<Photo>,
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewPhoto: ImageView = itemView.findViewById(R.id.imageViewPhoto)
        val imageButtonRemovePhoto: ImageButton = itemView.findViewById(R.id.imageButtonRemovePhoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo_thumbnail, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        
        // Carregar a miniatura da foto
        val imageFile = if (photo.thumbnailUrl.isNotEmpty()) {
            File(photo.thumbnailUrl)
        } else {
            File(photo.url)
        }
        
        if (imageFile.exists()) {
            Glide.with(holder.imageViewPhoto)
                .load(imageFile)
                .centerCrop()
                .into(holder.imageViewPhoto)
        } else {
            // Imagem não encontrada, mostrar placeholder
            holder.imageViewPhoto.setImageResource(R.drawable.ic_image_placeholder)
        }
        
        // Configurar botão de remoção
        holder.imageButtonRemovePhoto.setOnClickListener {
            onDeleteClick(photo.id)
        }
        
        // Mostrar o botão de remoção ao clicar na miniatura
        holder.imageViewPhoto.setOnClickListener {
            holder.imageButtonRemovePhoto.visibility = 
                if (holder.imageButtonRemovePhoto.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }
    
    /**
     * Atualiza a lista de fotos
     */
    fun updatePhotos(newPhotos: List<Photo>) {
        this.photos = newPhotos
        notifyDataSetChanged()
    }
}