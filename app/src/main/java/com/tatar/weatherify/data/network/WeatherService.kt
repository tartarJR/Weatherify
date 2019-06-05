package com.tatar.weatherify.data.network

import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class WeatherService(private val weatherApi: WeatherApi) {

    fun getFourDaysWeatherForecast() {
        val call = weatherApi.getFourDaysWeatherForecast()
        call.enqueue(object : Callback<WeatherForecastResponse> {
            override fun onResponse(call: Call<WeatherForecastResponse>, response: Response<WeatherForecastResponse>) {
                if (response.isSuccessful) {
                    Timber.i("RESPONSE:%s", response.body()?.forecasts?.get(0)?.date)
                    Timber.i("RESPONSE:%s", response.body()?.forecasts?.get(1)?.date)
                    Timber.i("RESPONSE:%s", response.body()?.forecasts?.get(2)?.date)
                    Timber.i("RESPONSE:%s", response.body()?.forecasts?.get(3)?.date)
                } else {
                    Timber.e("Bad response")
                }
            }

            override fun onFailure(call: Call<WeatherForecastResponse>, t: Throwable) {
                Timber.e("Unable to make GET request to API: %s", t.message)
            }
        })
    }
}