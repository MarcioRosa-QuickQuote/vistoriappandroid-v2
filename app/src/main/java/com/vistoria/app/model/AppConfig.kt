package com.vistoria.app.model

data class AppConfig(
    var nomeVistoriador: String = "Seu Nome",
    var tema: String = "Amarelo", // Por enquanto deixamos só o amarelo
    var logoPath: String = "default_logo.png" // Depois te passo o default certinho
)
