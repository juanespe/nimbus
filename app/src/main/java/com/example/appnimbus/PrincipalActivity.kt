package com.example.appnimbus

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class PrincipalActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_principal)

            val imageView1 = findViewById<ImageView>(R.id.imageView23)
            val imageView2 = findViewById<ImageView>(R.id.imageView19)
            val imageView3 = findViewById<ImageView>(R.id.imageView20)
            val imageView4 = findViewById<ImageView>(R.id.imageView21)
            val imageView5 = findViewById<ImageView>(R.id.imageView22)
            val imageView6 = findViewById<ImageView>(R.id.imageView18)


            val url1 = "https://cdn-icons-png.flaticon.com/512/742/742749.png"
            val url2 = "https://cdn-icons-png.flaticon.com/512/742/742751.png"
            val url3 = "https://cdn-icons-png.flaticon.com/512/742/742774.png"
            val url4 = "https://cdn-icons-png.flaticon.com/512/742/742752.png"
            val url5 = "https://cdn-icons-png.flaticon.com/512/742/742780.png"
            val url6 = "https://cdn-icons-png.flaticon.com/512/742/742757.png"


            Picasso.get()
                .load(url1)
                .into(imageView1)

            Picasso.get()
                .load(url2)
                .into(imageView2)

            Picasso.get()
                .load(url3)
                .into(imageView3)

            Picasso.get()
                .load(url4)
                .into(imageView4)

            Picasso.get()
                .load(url5)
                .into(imageView5)

            Picasso.get()
                .load(url6)
                .into(imageView6)
        }
}