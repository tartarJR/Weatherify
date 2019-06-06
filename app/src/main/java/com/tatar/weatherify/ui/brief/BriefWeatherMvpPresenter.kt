package com.tatar.weatherify.ui.brief

import com.tatar.weatherify.ui.base.BaseMvpPresenter

interface BriefWeatherMvpPresenter : BaseMvpPresenter<BriefWeatherMvpView?> {
    fun retrieveWeatherForecastInformation()
    fun clearDisposable()
    fun disposeDisposable()
}