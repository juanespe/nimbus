package com.example.appnimbus

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    buttonGuardar.setOnClickListener {
      val titulo = editTextTitulo.text.toString()
      val contenido = editTextContenido.text.toString()
      val nota = Nota(
        id = null,
        title= titulo,
        content = contenido,
        customer = 345L //
      )

      val apiService = ApiClient.retrofit.create(NotasApiService::class.java)
      apiService.guardarNota(nota).enqueue(object : Callback<Nota> {
        override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
          if (response.isSuccessful) {
            Toast.makeText(this@NotaActivity, "Nota guardada con éxito", Toast.LENGTH_SHORT).show()
            finish()
          } else {
            Toast.makeText(this@NotaActivity, "Error al guardar la nota", Toast.LENGTH_SHORT).show()
          }
        }

        override fun onFailure(call: Call<Nota>, t: Throwable) {
          Toast.makeText(this@NotaActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
        }
      })
    }
  }
}
