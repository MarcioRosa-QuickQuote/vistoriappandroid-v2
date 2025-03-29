package com.vistoria.app.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vistoria.app.models.AppConfig
import com.vistoria.app.models.Inspection
import com.vistoria.app.models.Room
import java.util.ArrayList

/**
 * Gerenciador de preferências e estado do aplicativo
 */
class PreferenceManager(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    /**
     * Obtém ou cria a configuração do aplicativo
     */
    fun getConfig(): AppConfig {
        val configJson = preferences.getString(KEY_CONFIG, null)
        return if (configJson != null) {
            try {
                gson.fromJson(configJson, AppConfig::class.java)
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao analisar configuração: ${e.message}")
                createDefaultConfig()
            }
        } else {
            createDefaultConfig()
        }
    }

    /**
     * Salva a configuração do aplicativo
     */
    fun saveConfig(config: AppConfig) {
        try {
            val configJson = gson.toJson(config)
            preferences.edit().putString(KEY_CONFIG, configJson).apply()
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao salvar configuração: ${e.message}")
        }
    }

    /**
     * Cria a configuração padrão
     */
    private fun createDefaultConfig(): AppConfig {
        val defaultConfig = AppConfig.createDefault()
        saveConfig(defaultConfig)
        return defaultConfig
    }

    /**
     * Obtém a inspeção atual em andamento
     */
    fun getCurrentInspection(): Inspection {
        val inspectionJson = preferences.getString(KEY_CURRENT_INSPECTION, null)
        return if (inspectionJson != null) {
            try {
                gson.fromJson(inspectionJson, Inspection::class.java)
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao analisar inspeção atual: ${e.message}")
                createNewInspection()
            }
        } else {
            createNewInspection()
        }
    }

    /**
     * Salva a inspeção atual
     */
    fun saveCurrentInspection(inspection: Inspection) {
        try {
            val inspectionJson = gson.toJson(inspection)
            preferences.edit().putString(KEY_CURRENT_INSPECTION, inspectionJson).apply()
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao salvar inspeção atual: ${e.message}")
        }
    }

    /**
     * Limpa a inspeção atual
     */
    fun clearCurrentInspection() {
        try {
            preferences.edit().remove(KEY_CURRENT_INSPECTION).apply()
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao limpar inspeção atual: ${e.message}")
        }
    }

    /**
     * Cria uma nova inspeção
     */
    private fun createNewInspection(): Inspection {
        val config = getConfig()
        val defaultRooms = mutableListOf<Room>()
        
        // Adicionar salas padrão da configuração
        config.defaultRooms.forEach { roomName ->
            defaultRooms.add(
                Room(
                    id = System.currentTimeMillis().toString() + defaultRooms.size,
                    name = roomName,
                    description = "",
                    photos = ArrayList()
                )
            )
        }
        
        return Inspection(
            id = System.currentTimeMillis(),
            clientName = "",
            rooms = defaultRooms
        )
    }

    /**
     * Obtém o histórico de inspeções
     */
    fun getInspectionHistory(): List<Inspection> {
        val historyJson = preferences.getString(KEY_INSPECTION_HISTORY, null)
        return if (historyJson != null) {
            try {
                val type = object : TypeToken<List<Inspection>>() {}.type
                gson.fromJson(historyJson, type)
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao analisar histórico de inspeções: ${e.message}")
                ArrayList()
            }
        } else {
            ArrayList()
        }
    }

    /**
     * Salva uma inspeção no histórico
     */
    fun saveInspectionToHistory(inspection: Inspection) {
        try {
            val history = getInspectionHistory().toMutableList()
            
            // Verificar se já existe uma inspeção com o mesmo ID
            val existingIndex = history.indexOfFirst { it.id == inspection.id }
            if (existingIndex >= 0) {
                // Atualizar inspeção existente
                history[existingIndex] = inspection
            } else {
                // Adicionar nova inspeção
                history.add(0, inspection) // Adicionar no início da lista (mais recente)
            }
            
            // Limitar o tamanho do histórico
            if (history.size > MAX_HISTORY_SIZE) {
                history.subList(MAX_HISTORY_SIZE, history.size).clear()
            }
            
            // Salvar histórico atualizado
            val historyJson = gson.toJson(history)
            preferences.edit().putString(KEY_INSPECTION_HISTORY, historyJson).apply()
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao salvar inspeção no histórico: ${e.message}")
        }
    }

    /**
     * Remove uma inspeção do histórico
     */
    fun removeInspectionFromHistory(inspectionId: Long): Boolean {
        try {
            val history = getInspectionHistory().toMutableList()
            
            // Encontrar e remover a inspeção
            val removed = history.removeIf { it.id == inspectionId }
            
            if (removed) {
                // Salvar histórico atualizado
                val historyJson = gson.toJson(history)
                preferences.edit().putString(KEY_INSPECTION_HISTORY, historyJson).apply()
            }
            
            return removed
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao remover inspeção do histórico: ${e.message}")
            return false
        }
    }

    /**
     * Limpa todo o histórico de inspeções
     */
    fun clearInspectionHistory() {
        try {
            preferences.edit().remove(KEY_INSPECTION_HISTORY).apply()
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao limpar histórico de inspeções: ${e.message}")
        }
    }

    companion object {
        private const val TAG = "PreferenceManager"
        private const val PREFERENCES_NAME = "vistoria_app_prefs"
        private const val KEY_CONFIG = "app_config"
        private const val KEY_CURRENT_INSPECTION = "current_inspection"
        private const val KEY_INSPECTION_HISTORY = "inspection_history"
        private const val MAX_HISTORY_SIZE = 50
    }
}