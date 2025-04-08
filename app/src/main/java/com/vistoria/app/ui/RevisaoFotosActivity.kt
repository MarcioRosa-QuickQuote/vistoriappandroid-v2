package com.vistoria.app.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vistoria.app.R

class RevisaoFotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revisao_fotos)

        val imageFoto = findViewById<ImageView>(R.id.imageFoto)
        val editDescricao = findViewById<EditText>(R.id.editDescricao)
        val buttonSalvarInfo = findViewById<Button>(R.id.buttonSalvarInfo)

        buttonSalvarInfo.setOnClickListener {
            Toast.makeText(this, "Informações salvas!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
