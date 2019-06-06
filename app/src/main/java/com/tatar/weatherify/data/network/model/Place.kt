package com.tatar.weatherify.data.network.model

data class Place(
    var name: String,
    var phenomenon: String,
    var tempmin: Int,
    var tempmax: Int?
)