package com.tatar.weatherify.di.brief.module

import com.tatar.weatherify.di.brief.scope.PerBrief
import com.tatar.weatherify.ui.brief.BriefWeatherMvpPresenter
import com.tatar.weatherify.ui.brief.BriefWeatherPresenter
import dagger.Binds
import dagger.Module

@Module
interface BriefModule {

    @PerBrief
    @Binds
    fun bindDetailWeatherPresenter(detailWeatherPresenter: BriefWeatherPresenter): BriefWeatherMvpPresenter
}