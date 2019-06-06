package com.tatar.weatherify.di.brief.component

import com.tatar.weatherify.di.app.component.AppComponent
import com.tatar.weatherify.di.brief.module.BriefModule
import com.tatar.weatherify.di.brief.scope.PerBrief
import com.tatar.weatherify.ui.brief.BriefWeatherActivity
import dagger.BindsInstance
import dagger.Component

@PerBrief
@Component(modules = [BriefModule::class], dependencies = [AppComponent::class])
interface BriefComponent {

    fun injectSearchActivity(briefWeatherActivity: BriefWeatherActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun briefWeatherActivity(briefWeatherActivity: BriefWeatherActivity): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): BriefComponent
    }
}