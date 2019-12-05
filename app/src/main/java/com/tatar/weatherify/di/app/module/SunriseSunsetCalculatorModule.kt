package com.tatar.weatherify.di.app.module

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator
import com.luckycatlabs.sunrisesunset.dto.Location
import com.tatar.weatherify.di.PerApp
import com.tatar.weatherify.util.SunriseSunsetUtil
import dagger.Module
import dagger.Provides

@Module
object SunriseSunsetCalculatorModule {

    private const val TALLINN_LATITUDE = "59.4370"
    private const val TALLINN_LONGITUDE = "24.7536"
    private const val TALLINN_TIME_ZONE = "Europe/Tallinn"

    @PerApp
    @Provides
    fun location(): Location {
        return Location(TALLINN_LATITUDE, TALLINN_LONGITUDE)
    }

    @PerApp
    @Provides
    fun sunriseSunsetCalculator(location: Location): SunriseSunsetCalculator {
        return SunriseSunsetCalculator(location, TALLINN_TIME_ZONE)
    }

    @PerApp
    @Provides
    fun sunriseSunsetUtil(sunriseSunsetCalculator: SunriseSunsetCalculator): SunriseSunsetUtil {
        return SunriseSunsetUtil(sunriseSunsetCalculator)
    }
}