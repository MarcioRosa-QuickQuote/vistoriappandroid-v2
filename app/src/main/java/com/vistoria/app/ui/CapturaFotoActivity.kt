package com.vistoria.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vistoria.app.databinding.ActivityCapturaFotoBinding

class CapturaFotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapturaFotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapturaFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ainda vamos implementar funcionalidades aqui
    }
}
