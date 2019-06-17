package com.tatar.weatherify.di.detail

import com.tatar.weatherify.ui.detail.DetailWeatherMvpPresenter
import com.tatar.weatherify.ui.detail.DetailWeatherPresenter
import com.tatar.weatherify.ui.detail.PlaceAdapter
import com.tatar.weatherify.ui.detail.WindAdapter
import dagger.Module
import dagger.Provides

@Module
object DetailModule {

    @JvmStatic
    @PerDetail
    @Provides
    fun placeAdapter(itemClickListener: PlaceAdapter.ItemClickListener): PlaceAdapter {
        return PlaceAdapter(itemClickListener)
    }

    @JvmStatic
    @PerDetail
    @Provides
    fun windAdapter(): WindAdapter {
        return WindAdapter()
    }

    @JvmStatic
    @PerDetail
    @Provides
    fun detailWeatherPresenter(): DetailWeatherMvpPresenter {
        return DetailWeatherPresenter()
    }
}