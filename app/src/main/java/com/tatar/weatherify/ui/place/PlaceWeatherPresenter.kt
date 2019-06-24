package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BasePresenter
import javax.inject.Inject

class PlaceWeatherPresenter @Inject constructor() : PlaceWeatherContract.Presenter,
    BasePresenter<PlaceWeatherContract.View>() {

    override fun displayPlaceWeatherInformation(dateString: String, place: Place, isDayLight: Boolean) {

        if (isDayLight) getViewOrThrow().setDayBgImage() else getViewOrThrow().setNightBgImage()
        getViewOrThrow().displayPlaceWeatherInfo(dateString, place)
    }
}