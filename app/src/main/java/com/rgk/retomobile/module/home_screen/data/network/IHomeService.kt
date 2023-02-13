package com.rgk.retomobile.module.home_screen.data.network

import com.rgk.retomobile.module.home_screen.data.model.Pelicula

interface IHomeService {
    interface IgetAllPeliculaResponse{
        fun onSuccess(respuesta : Pelicula)
        fun onError(codeError:Int,message:String)
    }
}