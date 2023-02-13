package com.rgk.retomobile.module.home_screen.ui
import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rgk.retomobile.module.home_screen.data.model.Pelicula
import com.rgk.retomobile.module.home_screen.data.network.IHomeService
import com.rgk.retomobile.module.home_screen.domain.GetAllPeliculaUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest{
    @RelaxedMockK
    private lateinit var getAllPeliculaUseCase: GetAllPeliculaUseCase

    @RelaxedMockK
    private lateinit var application: Application

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var response : IHomeService.IgetAllPeliculaResponse

    @get:Rule
    var rule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(getAllPeliculaUseCase,application)
        Dispatchers.setMain(Dispatchers.Unconfined)
        response = object : IHomeService.IgetAllPeliculaResponse{
            override fun onSuccess(respuesta: Pelicula) {

            }

            override fun onError(codeError: Int, message: String) {

            }
        }
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `cuando viewmodel se cree por primera vez y venga sin datos`() = runTest {
        //Given

        response.onSuccess(Pelicula())
        response.onError(3,"OK")

        coEvery { getAllPeliculaUseCase(response) } returns Unit

        //When
        homeViewModel.getAllPelicula()

        //Then
        assert(homeViewModel.resultGetAllPelicula.value == null)

    }
}