package com.tatar.weatherify.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private val dateFormat = SimpleDateFormat("EE, dd MMM yyyy", Locale.ENGLISH)

    @JvmStatic
    fun getFormattedDate(date: Date): String {
        return dateFormat.format(date)
    }
}