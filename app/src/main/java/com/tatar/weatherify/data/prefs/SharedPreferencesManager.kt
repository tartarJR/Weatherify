package com.tatar.weatherify.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.tatar.weatherify.data.network.model.WeatherForecastResponse


class SharedPreferencesManager(context: Context, private val gson: Gson) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(WEATHER_FORECAST_PREF_NAME, Context.MODE_PRIVATE)
    }

    /*
        Save latest weather forecast info to prefs in json format
        Used for the sake of simplicity as the weather forecast info is not too big(memory wise)
        Retrofit cache could be used but it is not stable
        Room, SqLite can be used for bigger amount of data
    */
    fun saveLatestWeatherForecastData(weatherForecastResponse: WeatherForecastResponse) {
        val weatherForecastResponseString = gson.toJson(weatherForecastResponse)
        sharedPreferences.edit().putString(PREF_KEY_WEATHER_FORECAST_CACHE, weatherForecastResponseString).apply()
    }

    fun getCachedWeatherForecastData(): WeatherForecastResponse? {
        val weatherForecastResponseString = sharedPreferences.getString(PREF_KEY_WEATHER_FORECAST_CACHE, null)

        return if (weatherForecastResponseString == null) {
            null
        } else {
            gson.fromJson(weatherForecastResponseString, WeatherForecastResponse::class.java)
        }
    }

    companion object {
        private const val WEATHER_FORECAST_PREF_NAME = "WEATHER_FORECAST_PREF"
        private const val PREF_KEY_WEATHER_FORECAST_CACHE = "WEATHER_FORECAST_CACHE"
    }
}