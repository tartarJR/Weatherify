package com.tatar.weatherify.data.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Place(
    var name: String,
    var phenomenon: String,
    var tempmin: Int,
    var tempmax: Int?
) : Parcelable