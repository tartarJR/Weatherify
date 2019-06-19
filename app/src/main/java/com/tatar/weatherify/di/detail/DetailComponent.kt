package com.tatar.weatherify.di.detail

import com.tatar.weatherify.di.app.component.AppComponent
import com.tatar.weatherify.ui.detail.DetailWeatherActivity
import com.tatar.weatherify.ui.detail.PlaceAdapter
import dagger.BindsInstance
import dagger.Component

@PerDetail
@Component(modules = [DetailModule::class], dependencies = [AppComponent::class])
interface DetailComponent {

    fun injectDetailWeatherActivity(detailWeatherActivity: DetailWeatherActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun detailWeatherActivity(detailWeatherActivity: DetailWeatherActivity): Builder

        @BindsInstance
        fun itemClickListener(itemClickListener: PlaceAdapter.ItemClickListener): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): DetailComponent
    }
}