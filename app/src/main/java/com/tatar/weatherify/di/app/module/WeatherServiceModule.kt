package com.tatar.weatherify.di.app.module

import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.di.PerApp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [NetworkModule::class])
object WeatherServiceModule {

    @PerApp
    @Provides
    fun weatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}