package com.example.appnimbus

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotaActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.escribir_nota)

    val editTextTitulo = findViewById<EditText>(R.id.editTextTitulo)
    val editTextContenido = findViewById<EditText>(R.id.editTextContent)
    val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)
    val botonBack = findViewById<Button>(R.id.button3) // Botón Back
    val imageViewEmocion = findViewById<ImageView>(R.id.imageView9) // Asegúrate de que este sea tu ImageView

    // Recibir la emoción y la URL de la imagen
    val emocion = intent.getStringExtra("emocion") ?: ""
    val imagenUrl = intent.getStringExtra("imagenUrl") ?: ""

    // Mostrar emoción en el título y bloquear edición
    editTextTitulo.setText(emocion)
    editTextTitulo.isEnabled = true

    // Mostrar la imagen con Picasso
    if (imagenUrl.isNotEmpty()) {
      Picasso.get().load(imagenUrl).into(imageViewEmocion)
    }

    // Guardar nota
    buttonGuardar.setOnClickListener {
      val titulo = editTextTitulo.text.toString()
      val contenido = editTextContenido.text.toString()

      val nota = Nota(
        id = null,
        title = titulo,
        content = contenido,
        customer = Customer(232)
      )

      val apiService = ApiClient.retrofit.create(NotasApiService::class.java)
      apiService.guardarNota(nota).enqueue(object : Callback<Nota> {
        override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
          if (response.isSuccessful) {
            Toast.makeText(this@NotaActivity, "Nota guardada con éxito", Toast.LENGTH_SHORT).show()
            finish()
          } else {
            Toast.makeText(this@NotaActivity, "Error al guardar la nota: ${response.code()}", Toast.LENGTH_SHORT).show()
          }
        }

        override fun onFailure(call: Call<Nota>, t: Throwable) {
          Toast.makeText(this@NotaActivity, "Fallo en la conexión: ${t.message}", Toast.LENGTH_SHORT).show()
        }
      })
    }

    // Botón Back
    botonBack.setOnClickListener {
      onBackPressed()
    }
  }

  override fun onBackPressed() {
    AlertDialog.Builder(this)
      .setTitle("¿Salir sin guardar?")
      .setMessage("¿Estás seguro de que quieres volver sin guardar la nota?")
      .setPositiveButton("Sí") { _, _ ->
        super.onBackPressed()
      }
      .setNegativeButton("No", null)
      .show()
  }
}
