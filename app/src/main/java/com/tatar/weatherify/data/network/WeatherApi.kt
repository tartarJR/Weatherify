package com.tatar.weatherify.data.network

import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import io.reactivex.Single
import retrofit2.http.GET


interface WeatherApi {

    @GET("estonia/forecast")
    fun getFourDaysWeatherForecast(): Single<WeatherForecastResponse>

    companion object {
        const val BASE_URL = "https://weather.aw.ee/api/"
    }
}