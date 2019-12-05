package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.data.prefs.SharedPreferencesManager
import com.tatar.weatherify.ui.base.BasePresenter
import com.tatar.weatherify.util.NetworkUtil
import com.tatar.weatherify.util.SunriseSunsetUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class BriefWeatherPresenter @Inject constructor(
    private val weatherApi: WeatherApi,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val networkUtil: NetworkUtil,
    private val sunriseSunsetUtil: SunriseSunsetUtil
) : BriefWeatherContract.Presenter, BasePresenter<BriefWeatherContract.View>() {

    private val subscriptions = CompositeDisposable()

    override fun onDataRequested() {
        setBackGroundImage()
        getViewOrThrow().hideFourDaysBriefWeatherInfo()
        showLoadingContent()

        if (networkUtil.hasInternetConnection()) onInternetConnection()
        else onNoInternetConnection()
    }

    private fun setBackGroundImage() {
        if (sunriseSunsetUtil.isDayLight()) getViewOrThrow().setDayBgImage()
        else getViewOrThrow().setNightBgImage()
    }

    private fun showLoadingContent() {
        getViewOrThrow().displayLoadingMessage()
        getViewOrThrow().showStatusTv()
        getViewOrThrow().showProgressBar()
    }

    private fun onInternetConnection() {
        subscriptions.add(
            weatherApi.getFourDaysWeatherForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { weatherForecastResponse -> onNetworkResponse(weatherForecastResponse) },
                    { error -> onNetworkError(error) }
                )
        )
    }

    private fun onNetworkResponse(weatherForecastResponse: WeatherForecastResponse) {
        hideLoadingContent()
        getViewOrThrow().showFourDaysBriefWeatherInfo(
            weatherForecastResponse,
            sunriseSunsetUtil.isDayLight()
        )
        sharedPreferencesManager.saveLatestWeatherForecastData(
            weatherForecastResponse
        )
    }

    private fun onNetworkError(error: Throwable) {
        Timber.e(error.localizedMessage)
        getViewOrThrow().hideFourDaysBriefWeatherInfo()
        hideLoadingContent()
        getViewOrThrow().displayErrorMessage()
        getViewOrThrow().showStatusTv()
    }

    private fun onNoInternetConnection() {
        if (sharedPreferencesManager.getCachedWeatherForecastData() == null) onNoCachedDataFound()
        else onCachedDataFound()
    }

    private fun onNoCachedDataFound() {
        getViewOrThrow().hideFourDaysBriefWeatherInfo()
        hideLoadingContent()
        getViewOrThrow().displayNoInternetWarning()
        getViewOrThrow().showStatusTv()
    }

    private fun onCachedDataFound() {
        hideLoadingContent()
        getViewOrThrow().showCachedDataDisplayedToast()
        getViewOrThrow().showFourDaysBriefWeatherInfo(
            sharedPreferencesManager.getCachedWeatherForecastData()!!,
            sunriseSunsetUtil.isDayLight()
        )
    }

    private fun hideLoadingContent() {
        getViewOrThrow().hideStatusTv()
        getViewOrThrow().hideProgressBar()
    }

    override fun navigateToDetailWeatherActivity(dailyWeather: DailyWeather) {
        getViewOrThrow().startDetailWeatherActivity(dailyWeather, sunriseSunsetUtil.isDayLight())
    }

    override fun clearDisposable() {
        subscriptions.clear()
    }

    override fun disposeDisposable() {
        subscriptions.dispose()
    }
}