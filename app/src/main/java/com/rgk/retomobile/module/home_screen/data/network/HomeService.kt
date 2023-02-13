package com.rgk.retomobile.module.home_screen.data.network

import com.rgk.retomobile.module.home_screen.data.model.Pelicula
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import javax.inject.Inject

class HomeService @Inject constructor(
    private val api: HomeClient
) {
    suspend fun getAllPelicula(): Call<Pelicula> = withContext(Dispatchers.IO){
        api.getAllPelicula()
    }
}