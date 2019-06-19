package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BaseMvpView

interface PlaceWeatherMvpView : BaseMvpView {
    fun displayPlaceWeatherInfo(dateString: String, place: Place)
}