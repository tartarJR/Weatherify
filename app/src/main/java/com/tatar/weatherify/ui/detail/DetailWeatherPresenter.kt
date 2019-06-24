package com.tatar.weatherify.ui.detail

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherInfo
import com.tatar.weatherify.ui.base.BasePresenter
import java.util.*
import javax.inject.Inject

class DetailWeatherPresenter @Inject constructor() : DetailWeatherMvpPresenter, BasePresenter<DetailWeatherMvpView>() {

    override fun displayDetailWeatherInformation(dailyWeather: DailyWeather, isDayLight: Boolean) {

        checkDetachedView()

        if (isDayLight) {
            view?.setDayBgImage()
            view?.setSwitchTextToDay()
            displayDayOrNightWeatherInfo(dailyWeather.date, dailyWeather.day)
        } else {
            view?.setNightBgImage()
            view?.setSwitchTextToNight()
            displayDayOrNightWeatherInfo(dailyWeather.date, dailyWeather.night)
        }
    }

    override fun initDayNightSwitch(isDayLight: Boolean) {

        checkDetachedView()

        view?.setDayNightSwitchChecked(isDayLight)

        if (isDayLight) view?.setSwitchTextToDay()
        else view?.setSwitchTextToNight()
    }

    private fun displayDayOrNightWeatherInfo(date: Date, dayOrNightInfo: WeatherInfo) {

        view?.displayWeatherInfoContainer(
            date,
            dayOrNightInfo.tempmax,
            dayOrNightInfo.tempmin,
            dayOrNightInfo.phenomenon,
            dayOrNightInfo.text
        )

        dayOrNightInfo.places?.let {
            if (it.isNotEmpty()) view?.displayPlacesContainer(it)
            else view?.hidePlacesContainer()
        }

        dayOrNightInfo.winds?.let {
            if (it.isNotEmpty()) view?.displayWindsContainer(it)
            else view?.hideWindsContainer()
        }

        dayOrNightInfo.sea?.let {
            if (it.isNotBlank()) view?.displaySeaContainer(it)
            else view?.hideSeaContainer()
        }

        dayOrNightInfo.peipsi?.let {
            if (it.isNotBlank()) view?.displayPeipsiContainer(it)
            else view?.hidePeipsiContainer()
        }
    }
}