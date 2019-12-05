package com.tatar.weatherify.di.place

import com.tatar.weatherify.di.PerActivity
import com.tatar.weatherify.ui.place.PlaceWeatherActivity
import dagger.Component

@PerActivity
@Component(modules = [PlaceModule::class])
interface PlaceComponent {
    fun inject(placeWeatherActivity: PlaceWeatherActivity)
}