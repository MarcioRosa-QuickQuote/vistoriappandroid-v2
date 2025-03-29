package com.vistoria.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vistoria.app.R
import com.vistoria.app.models.Room

/**
 * Adaptador para exibir a lista de ambientes (salas) em um RecyclerView
 */
class RoomAdapter(
    private var rooms: List<Room>,
    private val listener: RoomClickListener
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    /**
     * Interface para comunicação com o fragmento pai
     */
    interface RoomClickListener {
        fun onTakePhotoClick(room: Room, position: Int)
        fun onAddDescriptionClick(room: Room, position: Int)
        fun onRemovePhotoClick(roomId: String, photoId: String)
    }
    
    /**
     * ViewHolder para os itens de ambiente
     */
    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewRoomName: TextView = itemView.findViewById(R.id.textViewRoomName)
        val textViewRoomPhotoCount: TextView = itemView.findViewById(R.id.textViewRoomPhotoCount)
        val imageViewRoomStatus: ImageView = itemView.findViewById(R.id.imageViewRoomStatus)
        val buttonTakePhoto: Button = itemView.findViewById(R.id.buttonTakePhoto)
        val buttonAddDescription: Button = itemView.findViewById(R.id.buttonAddDescription)
        val recyclerViewRoomPhotos: RecyclerView = itemView.findViewById(R.id.recyclerViewRoomPhotos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        
        // Configurar o nome da sala
        holder.textViewRoomName.text = room.name
        
        // Configurar contagem de fotos
        val photoCount = room.photos.size
        holder.textViewRoomPhotoCount.text = "$photoCount ${
            if (photoCount == 1) "foto" else "fotos"
        }"
        
        // Configurar ícone de status
        val isDone = photoCount > 0
        holder.imageViewRoomStatus.setImageResource(
            if (isDone) R.drawable.ic_check else R.drawable.ic_pending
        )
        
        // Configurar botões
        holder.buttonTakePhoto.setOnClickListener {
            listener.onTakePhotoClick(room, position)
        }
        
        holder.buttonAddDescription.setOnClickListener {
            listener.onAddDescriptionClick(room, position)
        }
        
        // Configurar RecyclerView de fotos, se houver
        if (room.photos.isNotEmpty()) {
            holder.recyclerViewRoomPhotos.visibility = View.VISIBLE
            
            // Configurar o adaptador para as fotos
            val photoAdapter = PhotoAdapter(room.photos) { photoId ->
                listener.onRemovePhotoClick(room.id, photoId)
            }
            
            holder.recyclerViewRoomPhotos.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = photoAdapter
            }
        } else {
            holder.recyclerViewRoomPhotos.visibility = View.GONE
        }
    }
    
    /**
     * Atualiza a lista de ambientes
     */
    fun updateRooms(newRooms: List<Room>) {
        this.rooms = newRooms
        notifyDataSetChanged()
    }
}