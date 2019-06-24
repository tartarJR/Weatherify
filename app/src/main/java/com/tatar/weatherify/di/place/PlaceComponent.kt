package com.tatar.weatherify.di.place

import com.tatar.weatherify.di.app.component.AppComponent
import com.tatar.weatherify.ui.place.PlaceWeatherActivity
import dagger.BindsInstance
import dagger.Component

@PerPlace
@Component(modules = [PlaceModule::class], dependencies = [AppComponent::class])
interface PlaceComponent {

    fun injectPlaceWeatherActivity(placeWeatherActivity: PlaceWeatherActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun placeWeatherActivity(placeWeatherActivity: PlaceWeatherActivity): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): PlaceComponent
    }
}