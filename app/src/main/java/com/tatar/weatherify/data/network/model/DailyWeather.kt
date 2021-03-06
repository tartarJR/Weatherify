package com.tatar.weatherify.data.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class DailyWeather(
    val date: Date,
    val day: WeatherInfo,
    val night: WeatherInfo
) : Parcelable