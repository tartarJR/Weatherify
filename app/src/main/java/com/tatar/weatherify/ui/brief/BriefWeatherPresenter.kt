package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.data.network.model.DailyWeather
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

    override fun clearDisposable() {
        subscriptions.clear()
    }

    override fun disposeDisposable() {
        subscriptions.dispose()
    }

    override fun retrieveWeatherForecastInformation() {

        checkDetachedView()

        if (sunriseSunsetUtil.isDayLight()) view?.setDayBgImage() else view?.setNightBgImage()

        view?.hideFourDaysBriefWeatherInfo()
        showLoadingContent()

        if (networkUtil.hasInternetConnection()) {
            subscriptions.add(weatherApi.getFourDaysWeatherForecast()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { weatherForecastResponse ->
                        hideLoadingContent()
                        view?.showFourDaysBriefWeatherInfo(
                            weatherForecastResponse,
                            sunriseSunsetUtil.isDayLight()
                        )
                        sharedPreferencesManager.saveLatestWeatherForecastData(weatherForecastResponse)
                    },
                    { error ->
                        Timber.e(error.localizedMessage)
                        view?.hideFourDaysBriefWeatherInfo()
                        hideLoadingContent()
                        view?.displayErrorMessage()
                        view?.showStatusTv()
                    }
                ))
        } else {
            if (sharedPreferencesManager.getCachedWeatherForecastData() == null) {
                view?.hideFourDaysBriefWeatherInfo()
                hideLoadingContent()
                view?.displayNoInternetWarning()
                view?.showStatusTv()
            } else {
                hideLoadingContent()
                view?.showCachedDataDisplayedToast()
                view?.showFourDaysBriefWeatherInfo(
                    sharedPreferencesManager.getCachedWeatherForecastData()!!,
                    sunriseSunsetUtil.isDayLight()
                )
            }
        }
    }

    override fun navigateToDetailWeatherActivity(dailyWeather: DailyWeather) {
        checkDetachedView()
        view?.startDetailWeatherActivity(dailyWeather, sunriseSunsetUtil.isDayLight())
    }

    private fun showLoadingContent() {
        view?.displayLoadingMessage()
        view?.showStatusTv()
        view?.showProgressBar()
    }

    private fun hideLoadingContent() {
        view?.hideStatusTv()
        view?.hideProgressBar()
    }
}