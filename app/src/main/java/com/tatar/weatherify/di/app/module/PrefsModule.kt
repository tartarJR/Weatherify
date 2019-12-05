package com.tatar.weatherify.di.app.module

import android.content.Context
import com.google.gson.Gson
import com.tatar.weatherify.data.prefs.SharedPreferencesManager
import com.tatar.weatherify.di.PerApp
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
object PrefsModule {

    @PerApp
    @Provides
    fun sharedPreferencesManager(context: Context, gson: Gson): SharedPreferencesManager {
        return SharedPreferencesManager(context, gson)
    }
}