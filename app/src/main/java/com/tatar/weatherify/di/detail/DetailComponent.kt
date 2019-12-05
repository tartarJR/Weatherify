package com.tatar.weatherify.di.detail

import com.tatar.weatherify.di.PerActivity
import com.tatar.weatherify.di.app.AppComponent
import com.tatar.weatherify.ui.detail.DetailWeatherActivity
import com.tatar.weatherify.ui.detail.PlaceAdapter
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(modules = [DetailModule::class])
interface DetailComponent {

    fun inject(detailWeatherActivity: DetailWeatherActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun itemClickListener(itemClickListener: PlaceAdapter.ItemClickListener): Builder

        fun build(): DetailComponent
    }
}