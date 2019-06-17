package com.tatar.weatherify.ui.detail

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.ui.base.BaseMvpPresenter

interface DetailWeatherMvpPresenter : BaseMvpPresenter<DetailWeatherMvpView?> {
    fun displayDetailWeatherInformation(dailyWeather: DailyWeather, isDay: Boolean)
}