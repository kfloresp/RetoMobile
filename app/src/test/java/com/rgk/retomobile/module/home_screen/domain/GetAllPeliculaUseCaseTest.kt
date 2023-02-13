package com.rgk.retomobile.module.home_screen.domain

import com.rgk.retomobile.module.home_screen.data.model.Pelicula
import com.rgk.retomobile.module.home_screen.data.network.HomeRepository
import com.rgk.retomobile.module.home_screen.data.network.IHomeService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllPeliculaUseCaseTest{

    @RelaxedMockK
    private lateinit var homeRepository: HomeRepository

    lateinit var getAllPeliculaUseCase: GetAllPeliculaUseCase
    private lateinit var response : IHomeService.IgetAllPeliculaResponse

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getAllPeliculaUseCase = GetAllPeliculaUseCase(homeRepository)
        response = object : IHomeService.IgetAllPeliculaResponse{
            override fun onSuccess(respuesta: Pelicula) {

            }

            override fun onError(codeError: Int, message: String) {

            }
        }
    }

    @Test
    fun `Cuando llame al caso de uso se ejecute 1 vez el repositorio`() = runBlocking {

        //Given
        coEvery { homeRepository.getAllPelicula(response) } returns Unit

        //When
        getAllPeliculaUseCase(response)

        //Then
        coVerify(exactly = 1){
           homeRepository.getAllPelicula(response)
        }
        coVerify(exactly = 1) {
            getAllPeliculaUseCase.invoke(response)
        }
    }


}