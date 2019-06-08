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

    fun saveLatestWeatherForecastData(weatherForecastResponse: WeatherForecastResponse) {
        val weatherForecastResponseString = gson.toJson(weatherForecastResponse)
        sharedPreferences.edit().putString(PREF_KEY_WEATHER_FORECAST_CACHE, weatherForecastResponseString).apply()
    }

    fun getCachedWeatherForecastData(): WeatherForecastResponse {
        val weatherForecastResponseString = sharedPreferences.getString(PREF_KEY_WEATHER_FORECAST_CACHE, null)
        return gson.fromJson(weatherForecastResponseString, WeatherForecastResponse::class.java)
    }

    companion object {
        private const val WEATHER_FORECAST_PREF_NAME = "WEATHER_FORECAST_PREF"
        private const val PREF_KEY_WEATHER_FORECAST_CACHE = "WEATHER_FORECAST_CACHE"
    }
}