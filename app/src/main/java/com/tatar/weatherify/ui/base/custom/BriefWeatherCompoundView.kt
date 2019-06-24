package com.tatar.weatherify.ui.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.util.DateUtil
import com.tatar.weatherify.util.WeatherIconsUtil
import kotlinx.android.synthetic.main.view_brief_weather.view.*


class BriefWeatherCompoundView : ConstraintLayout {

    private lateinit var dailyWeather: DailyWeather
    private lateinit var place: Place
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
        setupDailyWeatherView()
    }

    fun getDailyWeather(): DailyWeather {
        return this.dailyWeather
    }

    private fun setupDailyWeatherView() {
        if (isDaylight) {
            populateViewWithWeatherInfo(
                DateUtil.getFormattedDate(dailyWeather.date, context.resources.getString(R.string.locale)),
                dailyWeather.day.tempmax,
                dailyWeather.day.tempmin,
                dailyWeather.day.phenomenon
            )
        } else {
            populateViewWithWeatherInfo(
                DateUtil.getFormattedDate(dailyWeather.date, context.resources.getString(R.string.locale)),
                dailyWeather.night.tempmax,
                dailyWeather.night.tempmin,
                dailyWeather.night.phenomenon
            )
        }
    }

    fun setPlace(dateString: String, place: Place) {
        this.place = place
        setupPlaceView(dateString)
    }

    private fun setupPlaceView(dateString: String) {
        populateViewWithWeatherInfo(dateString, place.tempmax, place.tempmin, place.phenomenon)
    }

    private fun populateViewWithWeatherInfo(dateString: String, tempMax: Int?, tempMin: Int?, phenomenon: String) {
        date_tv.text = dateString
        temp_max_view.setTemperature(tempMax)
        temp_min_view.setTemperature(tempMin)
        phenomenon_tv.text = phenomenon
        phenomenon_iv.setImageResource(WeatherIconsUtil.getWeatherIconByPhenomenon(phenomenon))
    }
}