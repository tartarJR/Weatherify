package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BaseMvpPresenter
import com.tatar.weatherify.util.SunriseSunsetUtil
import timber.log.Timber
import javax.inject.Inject

class PlaceWeatherPresenter @Inject constructor(
    private val sunriseSunsetUtil: SunriseSunsetUtil
) : PlaceWeatherMvpPresenter {

    private var placeWeatherMvpView: PlaceWeatherMvpView? = null

    override fun displayPlaceWeatherInformation(dateString: String, place: Place) {

        val isDay = sunriseSunsetUtil.isDayLight()

        if (isDay) placeWeatherMvpView?.setDayBgImage()
        else placeWeatherMvpView?.setNightBgImage()

        if (this.placeWeatherMvpView != null) {
            placeWeatherMvpView?.displayPlaceWeatherInfo(dateString, place)
        } else {
            Timber.e(BaseMvpPresenter.DETACHED_VIEW_ERROR)
        }
    }

    override fun attachView(view: PlaceWeatherMvpView?) {
        this.placeWeatherMvpView = view
    }

    override fun detachView() {
        this.placeWeatherMvpView = null
    }
}