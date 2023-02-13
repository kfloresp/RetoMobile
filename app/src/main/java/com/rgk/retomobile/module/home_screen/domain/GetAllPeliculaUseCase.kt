package com.rgk.retomobile.module.home_screen.domain

import com.rgk.retomobile.module.home_screen.data.network.HomeRepository
import com.rgk.retomobile.module.home_screen.data.network.IHomeService
import javax.inject.Inject

class GetAllPeliculaUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(igetAllPeliculaResponse: IHomeService.IgetAllPeliculaResponse)
    = repository.getAllPelicula(igetAllPeliculaResponse)

}