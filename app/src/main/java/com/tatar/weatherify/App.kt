package com.tatar.weatherify

import android.app.Application
import com.tatar.weatherify.di.app.component.AppComponent
import com.tatar.weatherify.di.app.component.DaggerAppComponent
import com.tatar.weatherify.util.CustomLogTree
import timber.log.Timber

class App : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        // Plant Timber for better logging
        Timber.plant(CustomLogTree("Weatherify-TAG"))

        appInstance = this

        appComponent = DaggerAppComponent.builder().applicationContext(this).build()
    }

    fun appComponent(): AppComponent {
        return appComponent
    }

    companion object {
        lateinit var appInstance: App private set
    }
}