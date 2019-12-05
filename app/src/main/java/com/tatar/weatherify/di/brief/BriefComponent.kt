package com.tatar.weatherify.di.brief

import com.tatar.weatherify.di.PerActivity
import com.tatar.weatherify.di.app.AppComponent
import com.tatar.weatherify.ui.brief.BriefWeatherActivity
import dagger.Component

@PerActivity
@Component(modules = [BriefModule::class], dependencies = [AppComponent::class])
interface BriefComponent {
    fun inject(briefWeatherActivity: BriefWeatherActivity)
}