package com.tatar.weatherify.di.place

import com.tatar.weatherify.di.detail.PerDetail
import com.tatar.weatherify.ui.place.PlaceWeatherMvpPresenter
import com.tatar.weatherify.ui.place.PlaceWeatherPresenter
import dagger.Binds
import dagger.Module

@Module
interface PlaceModule {

    @PerDetail
    @Binds
    fun bindPlaceWeatherPresenter(placeWeatherPresenter: PlaceWeatherPresenter): PlaceWeatherMvpPresenter
}