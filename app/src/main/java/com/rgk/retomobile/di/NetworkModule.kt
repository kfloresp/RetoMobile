package com.rgk.retomobile.di
import com.rgk.retomobile.GlobalApp
import com.rgk.retomobile.module.home_screen.data.network.HomeClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

     @Singleton
     @Provides
     fun provideRetrofit(): Retrofit {
         val interceptorLog = HttpLoggingInterceptor()
         interceptorLog.setLevel(HttpLoggingInterceptor.Level.BODY)
         val logger: OkHttpClient = OkHttpClient.Builder()
             .readTimeout(90, TimeUnit.SECONDS)
             .connectTimeout(90, TimeUnit.SECONDS)
             .connectTimeout(10, TimeUnit.SECONDS)
             .addInterceptor(interceptorLog)
             .build()
         return Retrofit.Builder()
             .baseUrl(GlobalApp.WebApi)
             .client(logger)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }

    @Singleton
    @Provides
    fun provideHomeClient(retrofit: Retrofit): HomeClient {
        return retrofit.create(HomeClient::class.java)
    }

}