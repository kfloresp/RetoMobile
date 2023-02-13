package com.rgk.retomobile.module.detail_screen.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rgk.retomobile.module.detail_screen.model.DetailModel
import com.rgk.retomobile.module.home_screen.data.model.PeliculaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    @Named("peliculaItem") val peliculaItem: PeliculaItem,
) : ViewModel() {

    val detailModelList = MutableLiveData<List<DetailModel>>()
    init {
        val list : MutableList<DetailModel> = arrayListOf()
        list.add(DetailModel("Titulo", peliculaItem.nombrePelicula))
        list.add(DetailModel("Sinopsis", peliculaItem.sinopsis))
        list.add(DetailModel("Actor(es)",
            peliculaItem.actor.joinToString {it.nombre} ))
        list.add(DetailModel("Director(es)",
            peliculaItem.director.joinToString {it.nombre} ))
        detailModelList.postValue(list)
    }
}