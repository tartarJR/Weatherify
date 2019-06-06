package com.tatar.weatherify.ui.brief

import android.view.View
import com.tatar.weatherify.App
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.di.brief.component.DaggerBriefComponent
import com.tatar.weatherify.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_brief_weather.*
import javax.inject.Inject

class BriefWeatherActivity : BaseActivity(), BriefWeatherMvpView {

    @Inject
    lateinit var briefWeatherMvpPresenter: BriefWeatherMvpPresenter

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
    }

    override fun init() {
        briefWeatherMvpPresenter.attachView(this)
        briefWeatherMvpPresenter.retrieveWeatherForecastInformation()
    }

    override fun releasePresenterResources() {
        briefWeatherMvpPresenter.detachView()
        briefWeatherMvpPresenter.disposeDisposable()
    }

    override fun onStop() {
        super.onStop()
        briefWeatherMvpPresenter.clearDisposable()
    }

    override fun showTitle() {
        four_days_weather_title_tv.visibility = View.VISIBLE
    }

    override fun hideTitle() {
        four_days_weather_title_tv.visibility = View.GONE
    }

    override fun showBriefWeatherCompoundViews() {
        first_daily_weather_brief_view.visibility = View.VISIBLE
        second_daily_weather_brief_view.visibility = View.VISIBLE
        third_daily_weather_brief_view.visibility = View.VISIBLE
        fourth_daily_weather_brief_view.visibility = View.VISIBLE
    }

    override fun hideBriefWeatherCompoundViews() {
        first_daily_weather_brief_view.visibility = View.GONE
        second_daily_weather_brief_view.visibility = View.GONE
        third_daily_weather_brief_view.visibility = View.GONE
        fourth_daily_weather_brief_view.visibility = View.GONE
    }

    override fun displayWeatherForecastInformation(weatherForecastResponse: WeatherForecastResponse) {
        first_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[0])
        second_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[1])
        third_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[2])
        fourth_daily_weather_brief_view.setDailyWeather(weatherForecastResponse.forecasts[3])
    }
}
