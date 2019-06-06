package com.tatar.weatherify.di.brief.module

import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.di.brief.scope.PerBrief
import com.tatar.weatherify.ui.brief.BriefWeatherMvpPresenter
import com.tatar.weatherify.ui.brief.BriefWeatherPresenter
import com.tatar.weatherify.util.NetworkUtil
import dagger.Module
import dagger.Provides

@Module
object BriefModule {

    @JvmStatic
    @PerBrief
    @Provides
    fun briefWeatherPresenter(
        weatherApi: WeatherApi,
        networkUtil: NetworkUtil
    ): BriefWeatherMvpPresenter {
        return BriefWeatherPresenter(weatherApi, networkUtil)
    }
}