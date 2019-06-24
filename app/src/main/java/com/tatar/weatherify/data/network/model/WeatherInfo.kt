package com.tatar.weatherify.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherInfo(
    val phenomenon: String,
    @SerializedName("tempmin") val tempMin: Int,
    @SerializedName("tempmax") val tempMax: Int,
    val text: String,
    val sea: String?,
    val peipsi: String?,
    val places: ArrayList<Place>?,
    val winds: ArrayList<Wind>?
) : Parcelable