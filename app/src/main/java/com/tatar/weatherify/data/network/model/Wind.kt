package com.tatar.weatherify.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(
    val name: String,
    val direction: String,
    @SerializedName("speedmin") val speedMin: Int,
    @SerializedName("speedmax") val speedMax: Int
) : Parcelable