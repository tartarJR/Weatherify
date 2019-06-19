package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BaseMvpPresenter

interface PlaceWeatherMvpPresenter : BaseMvpPresenter<PlaceWeatherMvpView?> {
    fun displayPlaceWeatherInformation(dateString: String, place: Place)
}