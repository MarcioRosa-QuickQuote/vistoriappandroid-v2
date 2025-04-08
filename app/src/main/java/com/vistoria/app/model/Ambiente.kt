package com.vistoria.app.model

data class Ambiente(
    val nome: String,
    val imagemPath: String,
    var qtdFotos: Int = 0,
    var isConcluido: Boolean = false
)
