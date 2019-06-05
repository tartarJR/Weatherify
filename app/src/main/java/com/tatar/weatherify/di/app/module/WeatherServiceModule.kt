package com.tatar.weatherify.di.app.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.data.network.WeatherService
import com.tatar.weatherify.di.app.scope.PerApp
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [NetworkModule::class])
object WeatherServiceModule {

    @JvmStatic
    @PerApp
    @Provides
    fun weatherService(weatherApi: WeatherApi): WeatherService {
        return WeatherService(weatherApi)
    }

    @JvmStatic
    @PerApp
    @Provides
    fun weatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @JvmStatic
    @PerApp
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(WeatherApi.BASE_URL)
            .build()
    }

    @JvmStatic
    @PerApp
    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder().setDateFormat("yyyy-MM-dd")
        return gsonBuilder.create()
    }
}