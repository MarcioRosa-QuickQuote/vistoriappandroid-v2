package com.vistoria.app.models

data class Inspection(
    val id: String,
    val date: String,
    val address: String,
    val rooms: MutableList<Room> = mutableListOf()
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "date" to date,
            "address" to address,
            "rooms" to rooms.map { it.toMap() }
        )
    }

    companion object {
        fun fromMap(map: Map<String, Any>): Inspection {
            val roomsList = (map["rooms"] as? List<Map<String, String>>)?.map {
                Room.fromMap(it)
            }?.toMutableList() ?: mutableListOf()

            return Inspection(
                id = map["id"] as String,
                date = map["date"] as String,
                address = map["address"] as String,
                rooms = roomsList
            )
        }
    }
}
