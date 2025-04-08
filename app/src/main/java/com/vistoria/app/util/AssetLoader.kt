package com.vistoria.app.util

import android.content.Context
import com.vistoria.app.model.Ambiente

object AssetLoader {
    fun loadAmbientes(context: Context): List<Ambiente> {
        val assetManager = context.assets
        val imageFiles = assetManager.list("ambientes") ?: return emptyList()

        return imageFiles.map { fileName ->
            val nomeAmbiente = when {
                fileName.contains("sala", ignoreCase = true) -> "Sala"
                fileName.contains("cozinha", ignoreCase = true) -> "Cozinha"
                fileName.contains("banheiro", ignoreCase = true) -> "Banheiro"
                fileName.contains("quarto", ignoreCase = true) -> "Quarto"
                else -> "Ambiente"
            }

            Ambiente(
                nome = nomeAmbiente,
                imagemPath = fileName,
                qtdFotos = 0, // Inicialmente sem fotos
                isConcluido = false // Inicialmente pendente
            )
        }
    }
}
