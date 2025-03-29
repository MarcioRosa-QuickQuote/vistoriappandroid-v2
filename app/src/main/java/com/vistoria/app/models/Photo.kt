package com.vistoria.app.models

import java.util.Date

/**
 * Modelo de dados que representa uma foto de um ambiente
 */
data class Photo(
    val id: String,
    val url: String,
    val thumbnailUrl: String,
    val timestamp: Date,
    var description: String = ""
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "url" to url,
            "thumbnailUrl" to thumbnailUrl,
            "timestamp" to timestamp.time,
            "description" to description
        )
    }
    
    companion object {
        fun fromMap(map: Map<String, Any?>): Photo {
            val timestamp = when (val time = map["timestamp"]) {
                is Long -> Date(time)
                is Number -> Date(time.toLong())
                is String -> try { Date(time.toLong()) } catch (e: Exception) { Date() }
                else -> Date()
            }
            
            return Photo(
                id = map["id"] as String,
                url = map["url"] as String,
                thumbnailUrl = (map["thumbnailUrl"] as? String) ?: "",
                timestamp = timestamp,
                description = (map["description"] as? String) ?: ""
            )
        }
    }
}