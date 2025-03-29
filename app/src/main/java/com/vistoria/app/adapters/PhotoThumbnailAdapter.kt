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
 * Adaptador para exibir miniaturas de fotos na tela da câmera
 */
class PhotoThumbnailAdapter(
    private var photos: List<Photo>,
    private val listener: PhotoThumbnailClickListener
) : RecyclerView.Adapter<PhotoThumbnailAdapter.PhotoThumbnailViewHolder>() {

    /**
     * Interface para comunicação com o fragmento pai
     */
    interface PhotoThumbnailClickListener {
        fun onThumbnailClick(photo: Photo, position: Int)
        fun onThumbnailDeleteClick(photo: Photo, position: Int)
    }

    inner class PhotoThumbnailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewThumbnail: ImageView = itemView.findViewById(R.id.imageViewThumbnail)
        val imageButtonDelete: ImageButton = itemView.findViewById(R.id.imageButtonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoThumbnailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_camera_thumbnail, parent, false)
        return PhotoThumbnailViewHolder(view)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoThumbnailViewHolder, position: Int) {
        val photo = photos[position]
        
        // Carregar a miniatura da foto
        val imageFile = if (photo.thumbnailUrl.isNotEmpty()) {
            File(photo.thumbnailUrl)
        } else {
            File(photo.url)
        }
        
        if (imageFile.exists()) {
            Glide.with(holder.imageViewThumbnail.context)
                .load(imageFile)
                .centerCrop()
                .into(holder.imageViewThumbnail)
        } else {
            // Imagem não encontrada, mostrar placeholder
            holder.imageViewThumbnail.setImageResource(R.drawable.ic_image_placeholder)
        }
        
        // Configurar clique na miniatura
        holder.imageViewThumbnail.setOnClickListener {
            listener.onThumbnailClick(photo, position)
            
            // Alternar visibilidade do botão de exclusão
            holder.imageButtonDelete.visibility = 
                if (holder.imageButtonDelete.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
        
        // Configurar clique no botão de exclusão
        holder.imageButtonDelete.setOnClickListener {
            listener.onThumbnailDeleteClick(photo, position)
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