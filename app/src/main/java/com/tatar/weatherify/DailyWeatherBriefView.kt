package com.tatar.weatherify

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.view_daily_weather_brief.view.*

class DailyWeatherBriefView : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_daily_weather_brief, this, true)
        setBackgroundResource(R.drawable.bg_view_daily_weather_brief)

        day_tv.text = "Thursday"
        date_tv.text = "30 May 2019"
        time_tv.text = "14:35"
        temperature_max_tv.text = "29"
        phenomenon_tv.text = "Clear"
        phenomenon_iv.setImageResource(R.drawable.clear_day)
    }
}