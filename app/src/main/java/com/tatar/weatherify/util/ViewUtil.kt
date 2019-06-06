package com.tatar.weatherify.util

import com.tatar.weatherify.R

object ViewUtil {

    // TODO update corresponding images properly
    // TODO use static variables or enums for phenomenons
    @JvmStatic
    fun getWeatherIconByPhenomenon(phenomenon: String) = when (phenomenon) {
        "Clear" -> R.drawable.sunny
        "Few clouds" -> R.drawable.partly_cloudy
        "Variable clouds" -> R.drawable.cloudy
        "Cloudy with clear spells" -> R.drawable.cloudy
        "Cloudy" -> R.drawable.cloudy
        "Light snow shower" -> R.drawable.snow
        "Moderate snow shower" -> R.drawable.snow
        "Heavy snow shower" -> R.drawable.snow
        "Light shower" -> R.drawable.snow
        "Moderate shower" -> R.drawable.clear_day
        "Heavy shower" -> R.drawable.clear_day
        "Light rain" -> R.drawable.rain
        "Moderate rain" -> R.drawable.rain
        "Heavy rain" -> R.drawable.rain
        "Risk of glaze" -> R.drawable.clear_day
        "Light sleet" -> R.drawable.sleet
        "Moderate sleet" -> R.drawable.sleet
        "Light snowfall" -> R.drawable.clear_day
        "Moderate snowfall" -> R.drawable.clear_day
        "Heavy snowfall" -> R.drawable.clear_day
        "Snowstorm" -> R.drawable.clear_day
        "Drifting snow" -> R.drawable.clear_day
        "Hail" -> R.drawable.clear_day
        "Mist" -> R.drawable.clear_day
        "Fog" -> R.drawable.fog
        "Thunder" -> R.drawable.clear_day
        "Thunderstorm" -> R.drawable.clear_day
        else -> {
            R.drawable.clear_day
        }
    }
}