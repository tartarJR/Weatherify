package com.tatar.weatherify.ui.base

import timber.log.Timber

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // TODO user friendly error
    fun checkDetachedView() {
        if (this.view == null) {
            Timber.e(DETACHED_VIEW_ERROR)
            return
        }
    }

    companion object {
        const val DETACHED_VIEW_ERROR = "View is detached/null"
    }
}