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
    lateinit var briefWeatherPresenter: BriefWeatherContract.Presenter

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        briefWeatherPresenter.onDataRequested()
    }

    override fun onStop() {
        super.onStop()
        briefWeatherPresenter.clearDisposable()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_brief_weather
    }

    override fun provideDependencies() {
        DaggerBriefComponent.builder()
            .appComponent((application as App).appComponent())
            .build()
            .inject(this)
    }

    override fun init() {
        setupViews()
        briefWeatherPresenter.attachView(this)
        briefWeatherPresenter.onDataRequested()
    }

    private fun setupViews() {
        swipe_refresh_layout.setOnRefreshListener {
            briefWeatherPresenter.onDataRequested()
            swipe_refresh_layout.isRefreshing = false
        }

        first_brief_weather_view.setOnClickListener {
            briefWeatherPresenter.navigateToDetailWeatherActivity(first_brief_weather_view.getDailyWeather())
        }

        second_brief_weather_view.setOnClickListener {
            briefWeatherPresenter.navigateToDetailWeatherActivity(second_brief_weather_view.getDailyWeather())
        }

        third_brief_weather_view.setOnClickListener {
            briefWeatherPresenter.navigateToDetailWeatherActivity(third_brief_weather_view.getDailyWeather())
        }

        fourth_brief_weather_view.setOnClickListener {
            briefWeatherPresenter.navigateToDetailWeatherActivity(fourth_brief_weather_view.getDailyWeather())
        }
    }

    override fun releasePresenterResources() {
        briefWeatherPresenter.detachView()
        briefWeatherPresenter.disposeDisposable()
    }

    override fun showFourDaysBriefWeatherInfo(
        weatherForecastResponse: WeatherForecastResponse,
        isDayLight: Boolean
    ) {
        setLabelsVisibility(View.VISIBLE)
        setBriefWeatherViewsVisibility(View.VISIBLE)
        setBriefWeatherViewsData(weatherForecastResponse, isDayLight)
    }

    private fun setBriefWeatherViewsData(
        weatherForecastResponse: WeatherForecastResponse,
        isDayLight: Boolean
    ) {
        first_brief_weather_view.setDailyWeather(weatherForecastResponse.forecasts[0], isDayLight)
        second_brief_weather_view.setDailyWeather(weatherForecastResponse.forecasts[1], isDayLight)
        third_brief_weather_view.setDailyWeather(weatherForecastResponse.forecasts[2], isDayLight)
        fourth_brief_weather_view.setDailyWeather(weatherForecastResponse.forecasts[3], isDayLight)
    }

    override fun hideFourDaysBriefWeatherInfo() {
        setLabelsVisibility(View.GONE)
        setBriefWeatherViewsVisibility(View.GONE)
    }

    private fun setLabelsVisibility(visibilityStatus: Int) {
        four_days_weather_title_tv.visibility = visibilityStatus
        brief_weather_hint_tv.visibility = visibilityStatus
    }

    private fun setBriefWeatherViewsVisibility(visibilityStatus: Int) {
        first_brief_weather_view.visibility = visibilityStatus
        second_brief_weather_view.visibility = visibilityStatus
        third_brief_weather_view.visibility = visibilityStatus
        fourth_brief_weather_view.visibility = visibilityStatus
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
