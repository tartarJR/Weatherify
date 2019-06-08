package com.tatar.weatherify.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.ui.brief.BriefWeatherActivity

class DetailWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_weather)

        val dailyWeather = intent?.extras?.getParcelable<DailyWeather>(BriefWeatherActivity.KEY_SELECTED_DAILY_WEATHER)

        Toast.makeText(this, dailyWeather?.date.toString(), Toast.LENGTH_LONG).show()
    }
}
