package com.vistoria.app.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.vistoria.app.R

class EditarAmbienteActivity : AppCompatActivity() {

    private lateinit var imageAmbiente: ImageView
    private lateinit var buttonAlterarImagem: Button
    private lateinit var editNomeAmbiente: EditText
    private lateinit var switchAmbienteAtivo: Switch
    private lateinit var buttonSalvar: Button
    private lateinit var buttonCancelar: Button

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_ambiente)

        imageAmbiente = findViewById(R.id.imageAmbiente)
        buttonAlterarImagem = findViewById(R.id.buttonAlterarImagem)
        editNomeAmbiente = findViewById(R.id.editNomeAmbiente)
        switchAmbienteAtivo = findViewById(R.id.switchAmbienteAtivo)
        buttonSalvar = findViewById(R.id.buttonSalvar)
        buttonCancelar = findViewById(R.id.buttonCancelar)

        buttonAlterarImagem.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        buttonSalvar.setOnClickListener {
            Toast.makeText(this, "Ambiente salvo!", Toast.LENGTH_SHORT).show()
            finish()
        }

        buttonCancelar.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri = data.data!!
            val inputStream = contentResolver.openInputStream(imageUri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageAmbiente.setImageBitmap(bitmap)
        }
    }
}
