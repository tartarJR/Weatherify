package com.tatar.weatherify.di.detail

import com.tatar.weatherify.di.PerActivity
import com.tatar.weatherify.ui.detail.DetailWeatherContract
import com.tatar.weatherify.ui.detail.DetailWeatherPresenter
import dagger.Binds
import dagger.Module

@Module
interface DetailModule {

    @PerActivity
    @Binds
    fun bindDetailWeatherPresenter(detailWeatherPresenter: DetailWeatherPresenter): DetailWeatherContract.Presenter
}