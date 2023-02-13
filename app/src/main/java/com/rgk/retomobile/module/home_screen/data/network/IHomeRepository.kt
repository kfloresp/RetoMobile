package com.rgk.retomobile.module.home_screen.data.network

interface IHomeRepository {
    interface IgetAllPelicula{
        suspend fun getAllPelicula(igetAllPeliculaResponse: IHomeService.IgetAllPeliculaResponse)
    }
}