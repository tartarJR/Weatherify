package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.ui.base.BaseMvpPresenter

interface BriefWeatherMvpPresenter : BaseMvpPresenter<BriefWeatherMvpView?> {
    fun retrieveWeatherForecastInformation()
    fun navigateToDetailWeatherActivity(dailyWeather: DailyWeather)
    fun clearDisposable()
    fun disposeDisposable()
}