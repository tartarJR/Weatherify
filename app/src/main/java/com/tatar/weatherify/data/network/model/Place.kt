package com.tatar.weatherify.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
    val name: String,
    val phenomenon: String,
    @SerializedName("tempmin") val tempMin: Int?,
    @SerializedName("tempmax") val tempMax: Int?
) : Parcelable