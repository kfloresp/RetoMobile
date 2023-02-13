package com.rgk.retomobile.module.home_screen.data.network

import com.rgk.retomobile.module.home_screen.data.model.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val service: HomeService
) : IHomeRepository.IgetAllPelicula{
    
    override suspend fun getAllPelicula(igetAllPeliculaResponse: IHomeService.IgetAllPeliculaResponse) {
        try {
            val result: Call<Pelicula> = service.getAllPelicula()
            result.enqueue(object : Callback<Pelicula> {
                override fun onResponse(
                    call: Call<Pelicula>,
                    response: Response<Pelicula>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            val respuesta = response.body()!!
                            igetAllPeliculaResponse.onSuccess(respuesta)
                        } else {
                            igetAllPeliculaResponse.onError(3, "OnBodyError\nResponseCode: ${response.code()}")
                        }
                    } else {
                        igetAllPeliculaResponse.onError(3, "NotSuccess\nResponseCode: ${response.code()}")
                    }
                }

                override fun onFailure(
                    call: Call<Pelicula>,
                    t: Throwable
                ) {
                    igetAllPeliculaResponse.onError(3, "Exception: ${t.message}")
                }
            }
            )

        } catch (e: Exception) {
            igetAllPeliculaResponse.onError(3, "Exception: ${e.message}")
        }

    }

}