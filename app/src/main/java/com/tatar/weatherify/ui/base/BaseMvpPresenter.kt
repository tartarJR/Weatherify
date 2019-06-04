package com.tatar.weatherify.ui.base

interface BaseMvpPresenter<in T> {

    fun attachView(view: T)
    fun detachView()
    fun unSubscribeObservable()

    companion object {
        const val DETACHED_VIEW_ERROR = "View is detached"
    }
}