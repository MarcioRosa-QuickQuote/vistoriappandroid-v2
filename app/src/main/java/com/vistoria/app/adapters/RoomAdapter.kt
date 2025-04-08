package com.vistoria.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vistoria.app.R
import com.vistoria.app.models.Room
import com.vistoria.app.util.loadImageFromAsset

class RoomAdapter(
    private val rooms: List<Room>,
    private val onAddPhotoClick: (Room) -> Unit,
    private val onViewPhotosClick: (Room) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        holder.textViewRoomName.text = room.name
        holder.textViewRoomDescription.text = room.description
        holder.imageViewRoom.loadImageFromAsset(room.iconPath)

        holder.buttonAddPhoto.setOnClickListener {
            onAddPhotoClick(room)
        }

        holder.buttonViewPhotos.setOnClickListener {
            onViewPhotosClick(room)
        }
    }

    override fun getItemCount(): Int = rooms.size

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewRoomName: TextView = view.findViewById(R.id.textViewRoomName)
        val textViewRoomDescription: TextView = view.findViewById(R.id.textViewRoomDescription)
        val imageViewRoom: ImageView = view.findViewById(R.id.imageViewRoom)
        val buttonAddPhoto: Button = view.findViewById(R.id.buttonAddPhoto)
        val buttonViewPhotos: Button = view.findViewById(R.id.buttonViewPhotos)
    }
}
