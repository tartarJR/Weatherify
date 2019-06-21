package com.tatar.weatherify.util

import com.tatar.weatherify.R


object ViewUtil {

    // TODO update corresponding images properly
    // TODO use static variables or enums for phenomenons
    @JvmStatic
    fun getWeatherIconByPhenomenon(phenomenon: String) = when (phenomenon) {
        "Clear" -> R.drawable.clear
        "Few clouds" -> R.drawable.few_clouds
        "Variable clouds" -> R.drawable.variable_clouds
        "Cloudy with clear spells" -> R.drawable.coudy_with_clear_spells
        "Cloudy" -> R.drawable.cloudy
        "Light snow shower" -> R.drawable.light_snow_shower
        "Moderate snow shower" -> R.drawable.moderate_snow_shower
        "Heavy snow shower" -> R.drawable.heavy_snow_shower
        "Light shower" -> R.drawable.light_snow_shower
        "Moderate shower" -> R.drawable.moderate_shower
        "Heavy shower" -> R.drawable.heavy_shower
        "Light rain" -> R.drawable.light_rain
        "Moderate rain" -> R.drawable.moderate_rain
        "Heavy rain" -> R.drawable.heavy_rain
        "Risk of glaze" -> R.drawable.risk_of_glaze
        "Light sleet" -> R.drawable.light_sleet
        "Moderate sleet" -> R.drawable.moderatae_sleet
        "Light snowfall" -> R.drawable.light_snowfall
        "Moderate snowfall" -> R.drawable.moderatae_snowfall
        "Heavy snowfall" -> R.drawable.heavy_snowfall
        "Snowstorm" -> R.drawable.snow_storm
        "Drifting snow" -> R.drawable.drifting_snow
        "Hail" -> R.drawable.hail
        "Mist" -> R.drawable.mist
        "Fog" -> R.drawable.fog
        "Thunder" -> R.drawable.thunder
        "Thunderstorm" -> R.drawable.thunderstorm
        else -> {
            throw UnsupportedOperationException("Not supported yet.")
        }
    }
}