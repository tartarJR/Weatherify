package com.tatar.weatherify.ui.detail

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherInfo
import java.util.*
import javax.inject.Inject

class DetailWeatherPresenter @Inject constructor() : DetailWeatherMvpPresenter {

    private var detailWeatherMvpView: DetailWeatherMvpView? = null

    override fun displayDetailWeatherInformation(dailyWeather: DailyWeather, isDay: Boolean) {
        if (isDay) {
            detailWeatherMvpView?.setBgColorToDay()
            detailWeatherMvpView?.setSwitchTextToDay()
            displayDayOrNightWeatherInfo(dailyWeather.date, dailyWeather.day)
        } else {
            detailWeatherMvpView?.setBgColorToNight()
            detailWeatherMvpView?.setSwitchTextToNight()
            displayDayOrNightWeatherInfo(dailyWeather.date, dailyWeather.night)
        }
    }

    override fun attachView(view: DetailWeatherMvpView?) {
        this.detailWeatherMvpView = view
    }

    override fun detachView() {
        this.detailWeatherMvpView = null
    }

    private fun displayDayOrNightWeatherInfo(date: Date, dayOrNightInfo: WeatherInfo) {

        detailWeatherMvpView?.displayWeatherInfoContainer(
            date,
            dayOrNightInfo.tempmax,
            dayOrNightInfo.tempmin,
            dayOrNightInfo.phenomenon,
            dayOrNightInfo.text
        )

        dayOrNightInfo.places?.let {
            if (it.isNotEmpty()) detailWeatherMvpView?.displayPlacesContainer(it)
            else detailWeatherMvpView?.hidePlacesContainer()
        }

        dayOrNightInfo.winds?.let {
            if (it.isNotEmpty()) detailWeatherMvpView?.displayWindsContainer(it)
            else detailWeatherMvpView?.hideWindsContainer()
        }

        dayOrNightInfo.sea?.let {
            if (it.isNotBlank()) detailWeatherMvpView?.displaySeaContainer(it)
            else detailWeatherMvpView?.hideSeaContainer()
        }

        dayOrNightInfo.peipsi?.let {
            if (it.isNotBlank()) detailWeatherMvpView?.displayPeipsiContainer(it)
            else detailWeatherMvpView?.hidePeipsiContainer()
        }
    }
}