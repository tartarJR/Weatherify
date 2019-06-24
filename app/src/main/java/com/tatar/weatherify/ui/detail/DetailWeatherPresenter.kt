package com.tatar.weatherify.ui.detail

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherInfo
import com.tatar.weatherify.ui.base.BasePresenter
import java.util.*
import javax.inject.Inject

class DetailWeatherPresenter @Inject constructor() : DetailWeatherContract.Presenter,
    BasePresenter<DetailWeatherContract.View>() {

    override fun displayDetailWeatherInformation(dailyWeather: DailyWeather, isDayLight: Boolean) {

        if (isDayLight) {
            getViewOrThrow().setDayBgImage()
            getViewOrThrow().setSwitchTextToDay()
            displayDayOrNightWeatherInfo(dailyWeather.date, dailyWeather.day)
        } else {
            getViewOrThrow().setNightBgImage()
            getViewOrThrow().setSwitchTextToNight()
            displayDayOrNightWeatherInfo(dailyWeather.date, dailyWeather.night)
        }
    }

    override fun initDayNightSwitch(isDayLight: Boolean) {

        getViewOrThrow().setDayNightSwitchChecked(isDayLight)

        if (isDayLight) getViewOrThrow().setSwitchTextToDay()
        else getViewOrThrow().setSwitchTextToNight()
    }

    private fun displayDayOrNightWeatherInfo(date: Date, dayOrNightInfo: WeatherInfo) {

        getViewOrThrow().displayWeatherInfoContainer(
            date,
            dayOrNightInfo.tempMax,
            dayOrNightInfo.tempMin,
            dayOrNightInfo.phenomenon,
            dayOrNightInfo.text
        )

        dayOrNightInfo.places?.let {
            if (it.isNotEmpty()) getViewOrThrow().displayPlacesContainer(it)
            else getViewOrThrow().hidePlacesContainer()
        }

        dayOrNightInfo.winds?.let {
            if (it.isNotEmpty()) getViewOrThrow().displayWindsContainer(it)
            else getViewOrThrow().hideWindsContainer()
        }

        dayOrNightInfo.sea?.let {
            if (it.isNotBlank()) getViewOrThrow().displaySeaContainer(it)
            else getViewOrThrow().hideSeaContainer()
        }

        dayOrNightInfo.peipsi?.let {
            if (it.isNotBlank()) getViewOrThrow().displayPeipsiContainer(it)
            else getViewOrThrow().hidePeipsiContainer()
        }
    }
}