package com.rgk.retomobile.module.home_screen.data.network

import com.rgk.retomobile.module.home_screen.data.model.Pelicula
import retrofit2.Call
import retrofit2.http.GET

interface HomeClient {
    @GET("peliculas")
    fun getAllPelicula(
    ): Call<Pelicula>
}