package com.vistoria.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.vistoria.app.R
import com.vistoria.app.util.loadImageFromPath

class PhotoAdapter(
    private val photoPaths: MutableList<String>,
    private val onRemovePhoto: (String) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo_thumbnail, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val path = photoPaths[position]
        holder.imageViewPhoto.loadImageFromPath(path)
        holder.imageButtonRemovePhoto.setOnClickListener {
            onRemovePhoto(path)
        }
    }

    override fun getItemCount(): Int = photoPaths.size

    inner class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewPhoto: ImageView = view.findViewById(R.id.imageViewPhoto)
        val imageButtonRemovePhoto: ImageButton = view.findViewById(R.id.imageButtonRemovePhoto)
    }
}
