package com.rgk.retomobile.module.home_screen.ui

import MessageUtil
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgk.retomobile.module.home_screen.data.model.Pelicula
import com.rgk.retomobile.module.home_screen.data.model.PeliculaItem
import com.rgk.retomobile.module.home_screen.data.network.IHomeService
import com.rgk.retomobile.module.home_screen.domain.GetAllPeliculaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(
    private val getAllPeliculaUseCase: GetAllPeliculaUseCase,
    application: Application
) : ViewModel(){
    @SuppressLint("StaticFieldLeak")
    val context: Context = application.applicationContext

    val itemSelected = MutableLiveData<PeliculaItem>()

    fun onItemClicked(item :PeliculaItem){
        itemSelected.postValue(item)
    }

    var loadingGetAllPelicula = MutableLiveData<Boolean>()
    var resultGetAllPelicula = MutableLiveData<Pelicula>()

    fun getAllPelicula() = viewModelScope.launch(Dispatchers.IO) {
        loadingGetAllPelicula.postValue(true)
        getAllPeliculaUseCase.invoke(object:IHomeService.IgetAllPeliculaResponse{
            override fun onSuccess(respuesta: Pelicula) {
                resultGetAllPelicula.postValue(respuesta)
                loadingGetAllPelicula.postValue(false)
            }

            override fun onError(codeError: Int, message: String) {
               MessageUtil.showToast(context,codeError,message,1)
                loadingGetAllPelicula.postValue(false)
            }
        })
    }
}