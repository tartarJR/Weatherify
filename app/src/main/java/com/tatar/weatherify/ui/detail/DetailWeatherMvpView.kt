package com.tatar.weatherify.ui.detail

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.data.network.model.Wind
import com.tatar.weatherify.ui.base.BaseMvpView
import java.util.*

interface DetailWeatherMvpView : BaseMvpView {
    fun displayWeatherInfoContainer(
        date: Date,
        tempMax: Int,
        tempMin: Int,
        phenomenon: String,
        weatherInfo: String
    )

    fun displayPlacesContainer(places: List<Place>)
    fun displayWindsContainer(winds: List<Wind>)
    fun displaySeaContainer(sea: String)
    fun displayPeipsiContainer(peipsi: String)

    fun hideWeatherInfoContainer()
    fun hidePlacesContainer()
    fun hideWindsContainer()
    fun hideSeaContainer()
    fun hidePeipsiContainer()

    fun setSwitchTextToDay()
    fun setSwitchTextToNight()

    fun setBgColorToDay()
    fun setBgColorToNight()
}