package com.vistoria.app.models

/**
 * Modelo de configuração do aplicativo
 */
data class AppConfig(
    var inspectorName: String = "",
    var companyName: String = "",
    var logoUrl: String = "",
    var primaryColor: String = "#4CAF50",
    var accentColor: String = "#FF9800",
    var backgroundColor: String = "#FFFFFF",
    var textColor: String = "#000000",
    var defaultRooms: List<String> = listOf()
) {
    companion object {
        /**
         * Cria uma configuração padrão
         */
        fun createDefault(): AppConfig {
            return AppConfig(
                primaryColor = "#4CAF50",
                accentColor = "#FF9800",
                backgroundColor = "#FFFFFF",
                textColor = "#000000",
                defaultRooms = listOf(
                    "Sala de Estar",
                    "Cozinha",
                    "Quarto Principal",
                    "Banheiro",
                    "Área Externa",
                    "Garagem"
                )
            )
        }
    }
    
    /**
     * Verifica se a configuração é válida
     */
    fun isValid(): Boolean {
        // Verificar se há pelo menos uma sala padrão
        return defaultRooms.isNotEmpty()
    }
    
    /**
     * Converte a configuração para um mapa
     */
    fun toMap(): Map<String, Any> {
        return mapOf(
            "inspectorName" to inspectorName,
            "companyName" to companyName,
            "logoUrl" to logoUrl,
            "primaryColor" to primaryColor,
            "accentColor" to accentColor,
            "backgroundColor" to backgroundColor,
            "textColor" to textColor,
            "defaultRooms" to defaultRooms
        )
    }
    
    /**
     * Cria uma configuração a partir de um mapa
     */
    fun fromMap(map: Map<String, Any?>): AppConfig {
        val defaultRoomsList = (map["defaultRooms"] as? List<String>) ?: createDefault().defaultRooms
        
        return AppConfig(
            inspectorName = (map["inspectorName"] as? String) ?: "",
            companyName = (map["companyName"] as? String) ?: "",
            logoUrl = (map["logoUrl"] as? String) ?: "",
            primaryColor = (map["primaryColor"] as? String) ?: "#4CAF50",
            accentColor = (map["accentColor"] as? String) ?: "#FF9800",
            backgroundColor = (map["backgroundColor"] as? String) ?: "#FFFFFF",
            textColor = (map["textColor"] as? String) ?: "#000000",
            defaultRooms = defaultRoomsList
        )
    }
}