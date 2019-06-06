package com.tatar.weatherify.di.app.component

import android.content.Context
import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.di.app.module.WeatherServiceModule
import com.tatar.weatherify.di.app.scope.PerApp
import com.tatar.weatherify.util.NetworkUtil
import dagger.BindsInstance
import dagger.Component

@PerApp
@Component(modules = [WeatherServiceModule::class])
interface AppComponent {
    fun weatherApi(): WeatherApi
    fun networkUtil(): NetworkUtil

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }
}