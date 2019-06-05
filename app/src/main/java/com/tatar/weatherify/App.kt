package com.tatar.weatherify

import android.app.Application
import com.tatar.weatherify.util.Utils
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appInstance = this

        // Plant Timber for better logging
        Timber.plant(Utils.LogEverythingTree("Weatherify-TAG"))
    }

    companion object {
        lateinit var appInstance: App private set
    }
}