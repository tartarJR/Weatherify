package com.tatar.weatherify.data.network.model

import java.time.LocalDate

data class DailyWeather(
    var date: LocalDate?,
    var day: WeatherInfo?,
    var night: WeatherInfo?
)