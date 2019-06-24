package com.tatar.weatherify.ui.base

interface BaseContract {

    interface View {
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

    interface Presenter<in V : View> {
        fun attachView(view: V)
        fun detachView()
    }
}