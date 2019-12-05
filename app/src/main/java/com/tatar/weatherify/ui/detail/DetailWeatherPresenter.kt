package com.tatar.weatherify.ui.detail

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.data.network.model.WeatherInfo
import com.tatar.weatherify.data.network.model.Wind
import com.tatar.weatherify.ui.base.BasePresenter
import java.util.*
import javax.inject.Inject

class DetailWeatherPresenter @Inject constructor() : DetailWeatherContract.Presenter,
    BasePresenter<DetailWeatherContract.View>() {

    override fun onDataRequested(dailyWeather: DailyWeather, isDayLight: Boolean) {
        if (isDayLight) onDataRequested(dailyWeather.date, dailyWeather.day)
        else onNightDataRequested(dailyWeather.date, dailyWeather.day)
    }

    private fun onDataRequested(date: Date, dayInfo: WeatherInfo) {
        getViewOrThrow().setDayBgImage()
        getViewOrThrow().setSwitchTextToDay()
        displayWeatherData(date, dayInfo)
    }

    private fun onNightDataRequested(date: Date, nightInfo: WeatherInfo) {
        getViewOrThrow().setNightBgImage()
        getViewOrThrow().setSwitchTextToNight()
        displayWeatherData(date, nightInfo)
    }

    private fun displayWeatherData(date: Date, dayOrNightInfo: WeatherInfo) {
        getViewOrThrow().displayWeatherInfoContainer(
            date,
            dayOrNightInfo.tempMax,
            dayOrNightInfo.tempMin,
            dayOrNightInfo.phenomenon,
            dayOrNightInfo.text
        )

        displayPlacesData(dayOrNightInfo.places)
        displayWindData(dayOrNightInfo.winds)
        displaySeaData(dayOrNightInfo.sea)
        displayPeipsiData(dayOrNightInfo.peipsi)
    }

    private fun displayPlacesData(places: List<Place>?) {
        places?.let {
            if (it.isNotEmpty()) getViewOrThrow().displayPlacesContainer(it)
            else getViewOrThrow().hidePlacesContainer()
        }
    }

    private fun displayWindData(winds: List<Wind>?) {
        winds?.let {
            if (it.isNotEmpty()) getViewOrThrow().displayWindsContainer(it)
            else getViewOrThrow().hideWindsContainer()
        }
    }

    private fun displaySeaData(sea: String?) {
        sea?.let {
            if (it.isNotBlank()) getViewOrThrow().displaySeaContainer(it)
            else getViewOrThrow().hideSeaContainer()
        }
    }

    private fun displayPeipsiData(peipsi: String?) {
        peipsi?.let {
            if (it.isNotBlank()) getViewOrThrow().displayPeipsiContainer(it)
            else getViewOrThrow().hidePeipsiContainer()
        }
    }

    override fun initSwitch(isDayLight: Boolean) {
        getViewOrThrow().setDayNightSwitchChecked(isDayLight)

        if (isDayLight) getViewOrThrow().setSwitchTextToDay()
        else getViewOrThrow().setSwitchTextToNight()
    }
}