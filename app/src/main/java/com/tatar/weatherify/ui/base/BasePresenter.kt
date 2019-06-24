package com.tatar.weatherify.ui.base


abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // TODO user friendly error
    fun getViewOrThrow(): V {
        return view ?: throw IllegalStateException("View is detached/null")
    }
}