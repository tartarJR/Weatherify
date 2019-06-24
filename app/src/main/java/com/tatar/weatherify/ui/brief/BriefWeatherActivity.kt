package com.tatar.weatherify.ui.brief

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.tatar.weatherify.App
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.di.brief.DaggerBriefComponent
import com.tatar.weatherify.ui.base.BaseActivity
import com.tatar.weatherify.ui.detail.DetailWeatherActivity
import kotlinx.android.synthetic.main.activity_brief_weather.*
import javax.inject.Inject


class BriefWeatherActivity : BaseActivity(), BriefWeatherContract.View {

    @Inject
    lateinit var briefWeatherMvpPresenter: BriefWeatherContract.Presenter

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        briefWeatherMvpPresenter.retrieveWeatherForecastInformation()
    }

    override fun onStop() {
        super.onStop()
        briefWeatherMvpPresenter.clearDisposable()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_brief_weather
    }

    override fun provideDependencies() {
        val briefComponent = DaggerBriefComponent.builder()
            .briefWeatherActivity(this)
            .appComponent((application as App).appComponent()).build()

        briefComponent.injectSearchActivity(this)
    }

    override fun init() {
        swipe_refresh_layout.setOnRefreshListener {
            briefWeatherMvpPresenter.retrieveWeatherForecastInformation()
            swipe_refresh_layout.isRefreshing = false
        }

        first_daily_weather_brief_view.setOnClickListener {
            briefWeatherMvpPresenter.navigateToDetailWeatherActivity(first_daily_weather_brief_view.getDailyWeather())
        }

        second_daily_weather_brief_view.setOnClickListener {
            briefWeatherMvpPresenter.navigateToDetailWeatherActivity(second_daily_weather_brief_view.getDailyWeather())
        }

        third_daily_weather_brief_view.setOnClickListener {
            briefWeatherMvpPresenter.navigateToDetailWeatherActivity(third_daily_weather_brief_view.getDailyWeather())
        }

        fourth_daily_weather_brief_view.setOnClickListener {
            briefWeatherMvpPresenter.navigateToDetailWeatherActivity(fourth_daily_weather_brief_view.getDailyWeather())
        }

        briefWeatherMvpPresenter.attachView(this)
        briefWeatherMvpPresenter.retrieveWeatherForecastInformation()
    }

    override fun releasePresenterResources() {
        briefWeatherMvpPresenter.detachView()
        briefWeatherMvpPresenter.disposeDisposable()
    }

    override fun showFourDaysBriefWeatherInfo(weatherForecastResponse: WeatherForecastResponse, isDayLight: Boolean) {
        four_days_weather_title_tv.visibility = View.VISIBLE
        brief_weather_hint_tv.visibility = View.VISIBLE

        first_daily_weather_brief_view.visibility = View.VISIBLE
        second_daily_weather_brief_view.visibility = View.VISIBLE
        third_daily_weather_brief_view.visibility = View.VISIBLE
        fourth_daily_weather_brief_view.visibility = View.VISIBLE

        first_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[0], isDayLight) // current day
        second_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[1], isDayLight) // 2nd day
        third_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[2], isDayLight) // 3rd day
        fourth_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[3], isDayLight) // 4th day
    }

    override fun hideFourDaysBriefWeatherInfo() {

        four_days_weather_title_tv.visibility = View.GONE
        brief_weather_hint_tv.visibility = View.GONE

        first_daily_weather_brief_view.visibility = View.GONE
        second_daily_weather_brief_view.visibility = View.GONE
        third_daily_weather_brief_view.visibility = View.GONE
        fourth_daily_weather_brief_view.visibility = View.GONE
    }

    override fun showCachedDataDisplayedToast() {
        Toast.makeText(this, getString(R.string.cached_data_displayed_toast_txt), Toast.LENGTH_LONG).show()
    }

    override fun startDetailWeatherActivity(dailyWeather: DailyWeather, isDayLight: Boolean) {
        val intent = Intent(this, DetailWeatherActivity::class.java)
        intent.putExtra(BUNDLE_KEY_SELECTED_DAILY_WEATHER, dailyWeather)
        intent.putExtra(BUNDLE_KEY_IS_DAY_LIGHT, isDayLight)
        startActivity(intent)
    }

    companion object {
        const val BUNDLE_KEY_SELECTED_DAILY_WEATHER = "selected_daily_weather"
        const val BUNDLE_KEY_IS_DAY_LIGHT = "is_day_light"
    }
}
