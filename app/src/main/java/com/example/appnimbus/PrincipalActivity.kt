package com.example.appnimbus

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso

class PrincipalActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_principal)

    cargarImagenesDesdeBackend()

    val emociones = listOf("Happy", "Very Happy", "Normal", "Bad", "Really bad", "Angry")
    val botones = listOf(
      findViewById<Button>(R.id.button8),
      findViewById<Button>(R.id.button13),
      findViewById<Button>(R.id.button9),
      findViewById<Button>(R.id.button10),
      findViewById<Button>(R.id.button11),
      findViewById<Button>(R.id.button12)
    )

    for ((index, boton) in botones.withIndex()) {
      boton.setOnClickListener {
        val intent = Intent(this, NotaActivity::class.java)
        intent.putExtra("emocion", emociones[index])  // Enviar emoción seleccionada
        startActivity(intent)
      }
    }
  }

  private fun cargarImagenesDesdeBackend() {
    val url = "http://10.0.2.2:8080/imagenes"

    val imageViews = listOf(
      findViewById<ImageView>(R.id.imageView23),
      findViewById<ImageView>(R.id.imageView19),
      findViewById<ImageView>(R.id.imageView20),
      findViewById<ImageView>(R.id.imageView21),
      findViewById<ImageView>(R.id.imageView22),
      findViewById<ImageView>(R.id.imageView18)
    )

    val queue = Volley.newRequestQueue(this)

    val jsonArrayRequest = JsonArrayRequest(
      url,
      { response ->
        for (i in 0 until minOf(response.length(), imageViews.size)) {
          val imageUrl = response.getString(i)
          Picasso.get().load(imageUrl).into(imageViews[i])
        }
      },
      { error ->
        Log.e("Volley", "Error al cargar imágenes: ${error.message}")
      }
    )

    queue.add(jsonArrayRequest)
  }
}
