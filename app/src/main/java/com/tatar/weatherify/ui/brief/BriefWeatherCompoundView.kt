package com.tatar.weatherify.ui.brief

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherInfo
import com.tatar.weatherify.util.DateUtil
import com.tatar.weatherify.util.ViewUtil
import kotlinx.android.synthetic.main.view_brief_weather.view.*
import java.util.*


class BriefWeatherCompoundView : ConstraintLayout {

    private lateinit var dailyWeather: DailyWeather
    private var isDaylight: Boolean = false

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_brief_weather, this, true)
        setBackgroundResource(R.drawable.bg_view_daily_weather_brief)
    }

    fun setDailyWeather(dailyWeather: DailyWeather, isDaylight: Boolean) {
        this.dailyWeather = dailyWeather
        this.isDaylight = isDaylight
        setupView()
    }

    fun getDailyWeather(): DailyWeather {
        return this.dailyWeather
    }

    private fun setupView() {
        if (isDaylight) {
            populateViewWithWeatherInfo(dailyWeather.date, dailyWeather.day)
        } else {
            populateViewWithWeatherInfo(dailyWeather.date, dailyWeather.night)
        }
    }

    private fun populateViewWithWeatherInfo(date: Date, dayOrNightInfo: WeatherInfo) {
        date_tv.text = DateUtil.getFormattedDate(date)
        temp_max_view.setTemperature(dayOrNightInfo.tempmax)
        temp_min_view.setTemperature(dayOrNightInfo.tempmin)
        phenomenon_tv.text = dayOrNightInfo.phenomenon
        phenomenon_iv.setImageResource(ViewUtil.getWeatherIconByPhenomenon(dayOrNightInfo.phenomenon))
    }
}