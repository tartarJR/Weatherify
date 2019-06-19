package com.tatar.weatherify.di.app.component

import android.content.Context
import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.data.prefs.SharedPreferencesManager
import com.tatar.weatherify.di.app.module.PrefsModule
import com.tatar.weatherify.di.app.module.SunriseSunsetCalculatorModule
import com.tatar.weatherify.di.app.module.WeatherServiceModule
import com.tatar.weatherify.di.app.scope.PerApp
import com.tatar.weatherify.util.NetworkUtil
import com.tatar.weatherify.util.SunriseSunsetUtil
import dagger.BindsInstance
import dagger.Component

@PerApp
@Component(modules = [WeatherServiceModule::class, PrefsModule::class, SunriseSunsetCalculatorModule::class])
interface AppComponent {
    fun weatherApi(): WeatherApi
    fun networkUtil(): NetworkUtil
    fun sharedPreferencesManager(): SharedPreferencesManager
    fun sunriseSunsetUtil(): SunriseSunsetUtil

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }
}