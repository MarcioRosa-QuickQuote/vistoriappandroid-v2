package com.vistoria.app.models

data class Room(
    val name: String,
    val description: String,
    val iconPath: String
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "name" to name,
            "description" to description,
            "iconPath" to iconPath
        )
    }

    companion object {
        fun fromMap(map: Map<String, String>): Room {
            return Room(
                name = map["name"] ?: "",
                description = map["description"] ?: "",
                iconPath = map["iconPath"] ?: ""
            )
        }
    }
}
