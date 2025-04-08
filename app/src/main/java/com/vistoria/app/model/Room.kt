package com.vistoria.app.model

data class Room(
    val name: String,
    var description: String = "",
    val photos: MutableList<String> = mutableListOf(),
    var isCompleted: Boolean = false
)
