package com.example.appnimbus

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class Nota(
    val id: Long?,
    val title: String,
    val content: String,
    val customer: Long
)
data class Customer(
    val id: Long
)

interface NotasApiService {

    @GET("notes")
    fun obtenerNotas(): Call<List<Nota>>

    @POST("notes")
    fun guardarNota(@Body nota: Nota): Call<Nota>
}