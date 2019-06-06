package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.ui.base.BaseMvpView

interface BriefWeatherMvpView : BaseMvpView {
    fun displayWeatherForecastInformation(weatherForecastResponse: WeatherForecastResponse)
    fun showTitle()
    fun hideTitle()
    fun showBriefWeatherCompoundViews()
    fun hideBriefWeatherCompoundViews()
}