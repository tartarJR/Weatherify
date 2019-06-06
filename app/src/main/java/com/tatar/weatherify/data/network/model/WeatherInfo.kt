package com.tatar.weatherify.data.network.model

class WeatherInfo(
    var phenomenon: String,
    var tempmin: Int,
    var tempmax: Int,
    var text: String,
    var sea: String?,
    var peipsi: String?,
    var places: ArrayList<Place>?,
    var winds: ArrayList<Wind>?
)