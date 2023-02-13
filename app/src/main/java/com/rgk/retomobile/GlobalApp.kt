package com.rgk.retomobile

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApp() : Application(){
    companion object {
        const val WebApi : String = "http://demo7211064.mockable.io/"
    }
}




