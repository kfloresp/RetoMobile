package com.rgk.retomobile.module.detail_screen

import androidx.lifecycle.SavedStateHandle
import com.rgk.retomobile.module.detail_screen.ui.DetailScreen
import com.rgk.retomobile.module.home_screen.data.model.PeliculaItem
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
    @Named("peliculaItem")
    fun itemPeliProvider(stateHandle: SavedStateHandle): PeliculaItem =
        stateHandle.get<PeliculaItem>(DetailScreen.CLAVE_ITEM)
            ?: throw IllegalStateException("not found in the state handle")
}