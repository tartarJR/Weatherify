package com.tatar.weatherify.ui.place

import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.ui.base.BaseMvpPresenter
import com.tatar.weatherify.ui.base.BaseMvpView

interface PlaceWeatherContract {

    interface View : BaseMvpView {
        fun displayPlaceWeatherInfo(dateString: String, place: Place)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun displayPlaceWeatherInformation(dateString: String, place: Place)
    }
}