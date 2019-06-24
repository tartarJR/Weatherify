package com.tatar.weatherify.ui.base

interface BaseMvpPresenter<in V : BaseMvpView> {
    fun attachView(view: V)
    fun detachView()
}