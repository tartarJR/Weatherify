package com.tatar.weatherify.ui.brief

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tatar.weatherify.App
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.di.brief.DaggerBriefComponent
import com.tatar.weatherify.ui.base.BaseActivity
import com.tatar.weatherify.ui.detail.DetailWeatherActivity
import kotlinx.android.synthetic.main.activity_brief_weather.*
import javax.inject.Inject


class BriefWeatherActivity : BaseActivity(), BriefWeatherMvpView {

    @Inject
    lateinit var briefWeatherMvpPresenter: BriefWeatherMvpPresenter

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
        val searchComponent = DaggerBriefComponent.builder()
            .briefWeatherActivity(this)
            .appComponent(App.appInstance.appComponent()).build()

        searchComponent.injectSearchActivity(this)
    }

    override fun initViews() {
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
    }

    override fun init() {
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

    override fun startDetailWeatherActivity(dailyWeather: DailyWeather) {
        val intent = Intent(this, DetailWeatherActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intent.putExtra(BUNDLE_KEY_SELECTED_DAILY_WEATHER, dailyWeather)
        startActivity(intent)
    }

    override fun setDayBgImage() {
        brief_weather_container.background = ContextCompat.getDrawable(this, R.drawable.bg_day)
    }

    override fun setNightBgImage() {
        brief_weather_container.background = ContextCompat.getDrawable(this, R.drawable.bg_night)
    }

    companion object {
        const val BUNDLE_KEY_SELECTED_DAILY_WEATHER = "selected_daily_weather"
    }
}
