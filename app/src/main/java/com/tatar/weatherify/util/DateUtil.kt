package com.tatar.weatherify.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    @JvmStatic
    fun getFormattedDate(date: Date, localeCode: String): String {
        return SimpleDateFormat("EE, dd MMM yyyy", Locale(localeCode)).format(date)
    }
}