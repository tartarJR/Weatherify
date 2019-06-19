package com.tatar.weatherify.util

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator
import java.util.*

class SunriseSunsetUtil(
    private val sunriseSunsetCalculator: SunriseSunsetCalculator
) {

    fun isDayLight(): Boolean {
        val now = Calendar.getInstance()
        val officialSunRise = sunriseSunsetCalculator.getOfficialSunriseCalendarForDate(now)
        val officialSunSet = sunriseSunsetCalculator.getOfficialSunsetCalendarForDate(now)

        return now > officialSunRise && now < officialSunSet
    }
}