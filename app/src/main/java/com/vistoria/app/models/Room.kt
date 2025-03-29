package com.vistoria.app.models

/**
 * Modelo de dados que representa um ambiente da vistoria
 */
data class Room(
    val id: String,
    val name: String,
    var description: String,
    val photos: MutableList<Photo>
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "description" to description,
            "photos" to photos.map { it.toMap() }
        )
    }
    
    companion object {
        fun fromMap(map: Map<String, Any?>): Room {
            val photos = (map["photos"] as? List<Map<String, Any?>>)?.map { 
                Photo.fromMap(it) 
            }?.toMutableList() ?: mutableListOf()
            
            return Room(
                id = map["id"] as String,
                name = map["name"] as String,
                description = (map["description"] as? String) ?: "",
                photos = photos
            )
        }
    }
}