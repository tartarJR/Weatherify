package com.tatar.weatherify.ui.brief

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.util.DateUtil
import com.tatar.weatherify.util.ViewUtil
import kotlinx.android.synthetic.main.view_brief_weather.view.*


class BriefWeatherCompoundView : ConstraintLayout {

    private lateinit var dailyWeather: DailyWeather

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_brief_weather, this, true)
        //TODO set different backgrounds depending on day time or night time
        setBackgroundResource(R.drawable.bg_view_daily_weather_brief)
    }

    fun setDailyWeather(dailyWeather: DailyWeather) {
        this.dailyWeather = dailyWeather
        setupView()
    }

    fun getDailyWeather(): DailyWeather {
        return this.dailyWeather
    }

    private fun setupView() {
        date_tv.text = DateUtil.getFormattedDate(dailyWeather.date)
        temp_max_view.setTemperature(dailyWeather.day.tempmax)
        temp_min_view.setTemperature(dailyWeather.day.tempmin)
        phenomenon_tv.text = dailyWeather.day.phenomenon
        phenomenon_iv.setImageResource(ViewUtil.getWeatherIconByPhenomenon(dailyWeather.day.phenomenon))
    }
}