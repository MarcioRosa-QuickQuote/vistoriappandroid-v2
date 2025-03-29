package com.vistoria.app.models

import java.util.Date

/**
 * Modelo de dados que representa uma vistoria completa
 */
data class Inspection(
    val id: Long,
    var clientName: String,
    val rooms: MutableList<Room>,
    val createdAt: Date = Date()
) {
    /**
     * Cria uma cópia da inspeção
     */
    fun copy(
        id: Long = this.id,
        clientName: String = this.clientName,
        rooms: MutableList<Room> = this.rooms.toMutableList(),
        createdAt: Date = this.createdAt
    ): Inspection {
        return Inspection(
            id = id,
            clientName = clientName,
            rooms = rooms,
            createdAt = createdAt
        )
    }
    
    /**
     * Converte a inspeção para um mapa
     */
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "clientName" to clientName,
            "rooms" to rooms.map { it.toMap() },
            "createdAt" to createdAt.time
        )
    }
    
    companion object {
        /**
         * Cria uma inspeção a partir de um mapa
         */
        fun fromMap(map: Map<String, Any?>): Inspection {
            val createdAt = when (val time = map["createdAt"]) {
                is Long -> Date(time)
                is Number -> Date(time.toLong())
                is String -> try { Date(time.toLong()) } catch (e: Exception) { Date() }
                else -> Date()
            }
            
            val roomsList = (map["rooms"] as? List<Map<String, Any?>>)?.map { 
                Room.fromMap(it) 
            }?.toMutableList() ?: mutableListOf()
            
            return Inspection(
                id = (map["id"] as? Number)?.toLong() ?: System.currentTimeMillis(),
                clientName = (map["clientName"] as? String) ?: "",
                rooms = roomsList,
                createdAt = createdAt
            )
        }
    }
}