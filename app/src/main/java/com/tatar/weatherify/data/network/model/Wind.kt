package com.tatar.weatherify.data.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(
    var name: String,
    var direction: String,
    var speedmin: Int,
    var speedmax: Int
) : Parcelable