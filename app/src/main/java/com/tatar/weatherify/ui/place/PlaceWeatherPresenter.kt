package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BasePresenter
import javax.inject.Inject

class PlaceWeatherPresenter @Inject constructor() : PlaceWeatherContract.Presenter,
    BasePresenter<PlaceWeatherContract.View>() {

    override fun onDataReceived(dateString: String, place: Place, isDayLight: Boolean) {
        setBackGroundImage(isDayLight)
        getViewOrThrow().displayPlaceWeatherInfo(dateString, place)
    }

    private fun setBackGroundImage(isDayLight: Boolean) {
        if (isDayLight) getViewOrThrow().setDayBgImage()
        else getViewOrThrow().setNightBgImage()
    }
}