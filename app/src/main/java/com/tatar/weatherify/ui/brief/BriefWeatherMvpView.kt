package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.ui.base.BaseMvpView

interface BriefWeatherMvpView : BaseMvpView {
    fun showTitle()
    fun hideTitle()
    fun showBriefWeatherCompoundViews()
    fun hideBriefWeatherCompoundViews()
    fun showCachedDataDisplayedToast()
    fun displayWeatherForecastInformation(weatherForecastResponse: WeatherForecastResponse)
    fun startDetailWeatherActivity(dailyWeather: DailyWeather)
}