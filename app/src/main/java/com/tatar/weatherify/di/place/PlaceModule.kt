package com.tatar.weatherify.di.place

import com.tatar.weatherify.ui.place.PlaceWeatherContract
import com.tatar.weatherify.ui.place.PlaceWeatherPresenter
import dagger.Binds
import dagger.Module

@Module
interface PlaceModule {

    @PerPlace
    @Binds
    fun bindPlaceWeatherPresenter(placeWeatherPresenter: PlaceWeatherPresenter): PlaceWeatherContract.Presenter
}