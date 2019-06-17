package com.tatar.weatherify.ui.detail

import androidx.recyclerview.widget.LinearLayoutManager
import com.tatar.weatherify.App
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.di.detail.DaggerDetailComponent
import com.tatar.weatherify.ui.base.BaseActivity
import com.tatar.weatherify.ui.brief.BriefWeatherActivity
import com.tatar.weatherify.util.DateUtil
import com.tatar.weatherify.util.ViewUtil
import kotlinx.android.synthetic.main.activity_detail_weather.*
import javax.inject.Inject

class DetailWeatherActivity : BaseActivity(), PlaceAdapter.ItemClickListener {

    @Inject
    lateinit var placeAdapter: PlaceAdapter

    @Inject
    lateinit var windAdapter: WindAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_weather
    }

    override fun provideDependencies() {
        val detailComponent = DaggerDetailComponent.builder()
            .searchActivity(this)
            .itemClickListener(this)
            .appComponent(App.appInstance.appComponent()).build()

        detailComponent.injectDetailWeatherActivity(this)
    }

    override fun initViews() {
        place_recycler_view.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        place_recycler_view.adapter = placeAdapter

        wind_recycler_view.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        wind_recycler_view.adapter = windAdapter

        val dailyWeather =
            intent?.extras?.getParcelable<DailyWeather>(BriefWeatherActivity.BUNDLE_KEY_SELECTED_DAILY_WEATHER)

        date_tv.text = DateUtil.getFormattedDate(dailyWeather?.date!!)
        temp_max_view.setTemperature(dailyWeather.day.tempmax)
        temp_min_view.setTemperature(dailyWeather.day.tempmin)
        phenomenon_iv.setImageResource(ViewUtil.getWeatherIconByPhenomenon(dailyWeather.day.phenomenon))
        phenomenon_tv.text = dailyWeather.day.phenomenon
        weather_tv.text = dailyWeather.day.text
        sea_tv.text = dailyWeather.day.sea
        peipsi_tv.text = dailyWeather.day.peipsi
        placeAdapter.setPlaces(dailyWeather.day.places!!)
        windAdapter.setPlaces(dailyWeather.day.winds!!)
    }

    override fun init() {

    }

    override fun releasePresenterResources() {

    }

    override fun onItemClick(place: Place) {

    }
}
