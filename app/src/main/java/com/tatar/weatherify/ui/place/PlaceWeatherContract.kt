package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BaseContract

interface PlaceWeatherContract {

    interface View : BaseContract.View {
        fun displayPlaceWeatherInfo(dateString: String, place: Place)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun displayPlaceWeatherInformation(dateString: String, place: Place)
    }
}