package com.tatar.weatherify.data.network.model

data class Wind(
    var name: String,
    var direction: String,
    var speedmin: Int,
    var speedmax: Int
)