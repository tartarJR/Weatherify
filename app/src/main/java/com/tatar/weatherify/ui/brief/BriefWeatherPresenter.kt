package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.prefs.SharedPreferencesManager
import com.tatar.weatherify.ui.base.BaseMvpPresenter
import com.tatar.weatherify.util.NetworkUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class BriefWeatherPresenter @Inject constructor(
    private val weatherApi: WeatherApi,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val networkUtil: NetworkUtil
) : BriefWeatherMvpPresenter {

    private var briefWeatherMvpView: BriefWeatherMvpView? = null
    private val subscriptions = CompositeDisposable()

    override fun attachView(view: BriefWeatherMvpView?) {
        this.briefWeatherMvpView = view
    }

    override fun detachView() {
        this.briefWeatherMvpView = null
    }

    override fun clearDisposable() {
        subscriptions.clear()
    }

    override fun disposeDisposable() {
        subscriptions.dispose()
    }

    override fun retrieveWeatherForecastInformation() {

        briefWeatherMvpView?.hideFourDaysBriefWeatherInfo()
        showLoadingContent()

        // TODO manage null view situation better
        if (this.briefWeatherMvpView != null) {
            if (networkUtil.hasInternetConnection()) {
                subscriptions.add(weatherApi.getFourDaysWeatherForecast()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { weatherForecastResponse ->
                            hideLoadingContent()
                            briefWeatherMvpView?.showFourDaysBriefWeatherInfo(weatherForecastResponse)
                            sharedPreferencesManager.saveLatestWeatherForecastData(weatherForecastResponse)
                        },
                        { error ->
                            Timber.e(error.localizedMessage)
                            briefWeatherMvpView?.hideFourDaysBriefWeatherInfo()
                            hideLoadingContent()
                            showErrorMessage()
                        }
                    ))
            } else {
                if (sharedPreferencesManager.getCachedWeatherForecastData() == null) {
                    briefWeatherMvpView?.hideFourDaysBriefWeatherInfo()
                    hideLoadingContent()
                    briefWeatherMvpView?.displayNoInternetWarning()
                    briefWeatherMvpView?.showStatusTv()
                } else {
                    hideLoadingContent()
                    briefWeatherMvpView?.showCachedDataDisplayedToast()
                    briefWeatherMvpView?.showFourDaysBriefWeatherInfo(sharedPreferencesManager.getCachedWeatherForecastData()!!)
                }
            }
        } else {
            showErrorMessage()
            Timber.e(BaseMvpPresenter.DETACHED_VIEW_ERROR)
        }
    }

    override fun navigateToDetailWeatherActivity(dailyWeather: DailyWeather) {
        briefWeatherMvpView?.startDetailWeatherActivity(dailyWeather)
    }

    private fun showLoadingContent() {
        briefWeatherMvpView?.displayLoadingMessage()
        briefWeatherMvpView?.showStatusTv()
        briefWeatherMvpView?.showProgressBar()
    }

    private fun hideLoadingContent() {
        briefWeatherMvpView?.hideStatusTv()
        briefWeatherMvpView?.hideProgressBar()
    }

    private fun showErrorMessage() {
        briefWeatherMvpView?.displayErrorMessage()
        briefWeatherMvpView?.showStatusTv()
    }
}