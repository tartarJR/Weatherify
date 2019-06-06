package com.tatar.weatherify.data.network.model

import java.util.*

data class DailyWeather(
    var date: Date,
    var day: WeatherInfo,
    var night: WeatherInfo
)