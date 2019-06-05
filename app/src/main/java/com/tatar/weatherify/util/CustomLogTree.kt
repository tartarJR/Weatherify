package com.tatar.weatherify.util

import android.util.Log
import timber.log.Timber

class CustomLogTree(private val logSuffix: String) : Timber.DebugTree() {
    override fun log(priority: Int, className: String?, message: String, throwable: Throwable?) {
        Log.println(priority, "$className-$logSuffix", message)
    }
}
