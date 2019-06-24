package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.ui.base.BaseContract

interface BriefWeatherContract {

    interface View : BaseContract.View {
        fun showFourDaysBriefWeatherInfo(weatherForecastResponse: WeatherForecastResponse, isDayLight: Boolean)
        fun hideFourDaysBriefWeatherInfo()
        fun showCachedDataDisplayedToast()
        fun startDetailWeatherActivity(dailyWeather: DailyWeather, isDayLight: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun retrieveWeatherForecastInformation()
        fun navigateToDetailWeatherActivity(dailyWeather: DailyWeather)
        fun clearDisposable()
        fun disposeDisposable()
    }
}