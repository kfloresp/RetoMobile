package com.rgk.retomobile.module.map_screen

import androidx.lifecycle.SavedStateHandle
import com.rgk.retomobile.module.detail_screen.ui.DetailScreen
import com.rgk.retomobile.module.home_screen.data.model.PeliculaItem
import com.rgk.retomobile.module.map_screen.ui.MapScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.lang.IllegalStateException
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailScreenModule{
    @Provides
    @Named("latitud")
    fun latitudProvider(stateHandle: SavedStateHandle): String =
        stateHandle.get<String>(MapScreen.CLAVE_LATITUD)
            ?: throw IllegalStateException("not found in the state handle")
    @Provides
    @Named("longitud")
    fun longitudProvider(stateHandle: SavedStateHandle): String =
        stateHandle.get<String>(MapScreen.CLAVE_LONGITUD)
            ?: throw IllegalStateException("not found in the state handle")
    @Provides
    @Named("pais")
    fun paisProvider(stateHandle: SavedStateHandle): String =
        stateHandle.get<String>(MapScreen.CLAVE_PAIS)
            ?: throw IllegalStateException("not found in the state handle")



}