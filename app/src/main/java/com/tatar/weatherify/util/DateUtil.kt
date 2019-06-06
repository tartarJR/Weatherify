package com.tatar.weatherify.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    private val timeFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    private val dayFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

    @JvmStatic
    fun getTime(): String {
        return timeFormat.format(Calendar.getInstance().time)
    }

    @JvmStatic
    fun getDay(date: Date): String {
        return dayFormat.format(date)
    }

    @JvmStatic
    fun getFormattedDate(date: Date): String {
        return dateFormat.format(date)
    }
}