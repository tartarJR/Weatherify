package com.tatar.weatherify.ui.detail

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.data.network.model.Wind
import com.tatar.weatherify.ui.base.BaseContract
import java.util.*

interface DetailWeatherContract {

    interface View : BaseContract.View {
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
        fun hidePlacesContainer()
        fun hideWindsContainer()
        fun hideSeaContainer()
        fun hidePeipsiContainer()
        fun setSwitchTextToDay()
        fun setSwitchTextToNight()
        fun setDayNightSwitchChecked(isDayLight: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun displayDetailWeatherInformation(dailyWeather: DailyWeather, isDayLight: Boolean)
        fun initDayNightSwitch(isDayLight: Boolean)
    }
}