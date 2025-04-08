package com.vistoria.app.utils

import android.content.Context
import com.vistoria.app.models.Room

class PreferenceManager(private val context: Context) {
    fun saveRoom(id: String, name: String, iconPath: String, photos: List<String>): Room {
        // lógica real de salvar pode ser implementada aqui
        return Room(name, "Descrição gerada", iconPath)
    }
}
