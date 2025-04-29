package com.example.appnimbus

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class ChatbotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatbot)

        val imageView = findViewById<ImageView>(R.id.imagen)
        val url = "https://genesistoxical.com/wp-content/uploads/2023/02/Corazon_sin_fondo.png"

        Picasso.get()
            .load(url)
            .into(imageView)
    }
}
