package com.tatar.weatherify.data.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class DailyWeather(
    var date: Date,
    var day: WeatherInfo,
    var night: WeatherInfo
) : Parcelable