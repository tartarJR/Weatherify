package com.tatar.weatherify.di.detail

import com.tatar.weatherify.ui.detail.DetailWeatherContract
import com.tatar.weatherify.ui.detail.DetailWeatherPresenter
import dagger.Binds
import dagger.Module

@Module
interface DetailModule {

    @PerDetail
    @Binds
    fun bindDetailWeatherPresenter(detailWeatherPresenter: DetailWeatherPresenter): DetailWeatherContract.Presenter
}