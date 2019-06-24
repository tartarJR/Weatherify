package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BasePresenter
import com.tatar.weatherify.util.SunriseSunsetUtil
import javax.inject.Inject

class PlaceWeatherPresenter @Inject constructor(
    private val sunriseSunsetUtil: SunriseSunsetUtil
) : PlaceWeatherContract.Presenter, BasePresenter<PlaceWeatherContract.View>() {

    override fun displayPlaceWeatherInformation(dateString: String, place: Place) {

        if (sunriseSunsetUtil.isDayLight()) getViewOrThrow().setDayBgImage()
        else getViewOrThrow().setNightBgImage()

        getViewOrThrow().displayPlaceWeatherInfo(dateString, place)
    }
}