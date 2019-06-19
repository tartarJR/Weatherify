package com.tatar.weatherify.ui.base

interface BaseMvpView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showStatusTv()
    fun hideStatusTv()
    fun displayLoadingMessage()
    fun displayErrorMessage()
    fun displayNoInternetWarning()
    fun setDayBgImage()
    fun setNightBgImage()
}