package com.tatar.weatherify.di.brief

import com.tatar.weatherify.di.PerActivity
import com.tatar.weatherify.ui.brief.BriefWeatherContract
import com.tatar.weatherify.ui.brief.BriefWeatherPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class BriefModule {

    @PerActivity
    @Binds
    abstract fun bindBriefWeatherPresenter(briefWeatherPresenter: BriefWeatherPresenter): BriefWeatherContract.Presenter
}