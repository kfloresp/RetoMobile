package com.rgk.retomobile.module.map_screen.ui
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class MapScreenViewModel @Inject constructor(
    @Named("latitud") val latitud: String,
    @Named("longitud") val longitud: String,
    @Named("pais") val pais: String
) : ViewModel() {
}