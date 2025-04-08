package com.vistoria.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vistoria.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Log inicial
        println("Aplicativo iniciado com sucesso!")

        // Log da versão do aplicativo
        Log.i("APP_VERSION", "Versão do App: ${BuildConfig.VERSION_NAME} (Code: ${BuildConfig.VERSION_CODE})")
    }
}
