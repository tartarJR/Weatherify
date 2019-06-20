package com.tatar.weatherify.ui.detail

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tatar.weatherify.App
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.DailyWeather
import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.data.network.model.Wind
import com.tatar.weatherify.di.detail.DaggerDetailComponent
import com.tatar.weatherify.ui.base.BaseActivity
import com.tatar.weatherify.ui.brief.BriefWeatherActivity
import com.tatar.weatherify.ui.place.PlaceWeatherActivity
import com.tatar.weatherify.util.DateUtil
import com.tatar.weatherify.util.ViewUtil
import kotlinx.android.synthetic.main.activity_detail_weather.*
import java.util.*
import javax.inject.Inject


class DetailWeatherActivity : BaseActivity(), DetailWeatherMvpView, PlaceAdapter.ItemClickListener {

    @Inject
    lateinit var placeAdapter: PlaceAdapter

    @Inject
    lateinit var windAdapter: WindAdapter

    @Inject
    lateinit var detailWeatherMvpPresenter: DetailWeatherMvpPresenter

    override fun getLayoutId(): Int {
        return R.layout.activity_detail_weather
    }

    override fun provideDependencies() {
        val detailComponent = DaggerDetailComponent.builder()
            .detailWeatherActivity(this)
            .itemClickListener(this)
            .appComponent((application as App).appComponent()).build()

        detailComponent.injectDetailWeatherActivity(this)
    }

    override fun initViews() {

        detailWeatherMvpPresenter.initDayNightSwitch(getIsDayLight())

        day_night_switch.setOnCheckedChangeListener { _, isDay ->
            detailWeatherMvpPresenter.displayDetailWeatherInformation(getDailyWeather(), isDay)
        }

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
    }

    override fun init() {
        detailWeatherMvpPresenter.attachView(this)
        detailWeatherMvpPresenter.displayDetailWeatherInformation(getDailyWeather(), getIsDayLight())
    }

    override fun releasePresenterResources() {
        detailWeatherMvpPresenter.detachView()
    }

    override fun displayWeatherInfoContainer(
        date: Date,
        tempMax: Int,
        tempMin: Int,
        phenomenon: String,
        weatherInfo: String
    ) {
        date_tv.text = DateUtil.getFormattedDate(date, getString(R.string.locale))
        temp_max_view.setTemperature(tempMax)
        temp_min_view.setTemperature(tempMin)
        phenomenon_iv.setImageResource(ViewUtil.getWeatherIconByPhenomenon(phenomenon))
        phenomenon_tv.text = phenomenon
        weather_tv.text = weatherInfo
        weather_info_container.visibility = View.VISIBLE
    }

    override fun displayPlacesContainer(places: List<Place>) {
        placeAdapter.setPlaces(places)
        places_container.visibility = View.VISIBLE
    }

    override fun displayWindsContainer(winds: List<Wind>) {
        windAdapter.setPlaces(winds)
        winds_container.visibility = View.VISIBLE
    }

    override fun displaySeaContainer(sea: String) {
        sea_tv.text = sea
        sea_info_container.visibility = View.VISIBLE
    }

    override fun displayPeipsiContainer(peipsi: String) {
        peipsi_tv.text = peipsi
        peipsi_info_container.visibility = View.VISIBLE
    }

    override fun hideWeatherInfoContainer() {
        weather_info_container.visibility = View.GONE
    }

    override fun hidePlacesContainer() {
        places_container.visibility = View.GONE
    }

    override fun hideWindsContainer() {
        winds_container.visibility = View.GONE
    }

    override fun hideSeaContainer() {
        sea_info_container.visibility = View.GONE
    }

    override fun hidePeipsiContainer() {
        peipsi_info_container.visibility = View.GONE
    }

    override fun setSwitchTextToDay() {
        day_night_switch.text = getString(R.string.day_night_switch_day_txt)
    }

    override fun setSwitchTextToNight() {
        day_night_switch.text = getString(R.string.day_night_switch_night_txt)
    }

    override fun setDayNightSwitchChecked(isDayLight: Boolean) {
        day_night_switch.isChecked = isDayLight
    }

    override fun onItemClick(place: Place) {
        val intent = Intent(this, PlaceWeatherActivity::class.java)
        intent.putExtra(BUNDLE_KEY_SELECTED_PLACE_WEATHER, place)
        intent.putExtra(BUNDLE_KEY_DATE, date_tv.text)
        startActivity(intent)
    }

    private fun getDailyWeather(): DailyWeather {
        return intent?.extras?.getParcelable(BriefWeatherActivity.BUNDLE_KEY_SELECTED_DAILY_WEATHER)!!
    }

    private fun getIsDayLight(): Boolean {
        return intent?.extras?.getBoolean(BriefWeatherActivity.BUNDLE_KEY_SELECTED_DAILY_WEATHER)!!
    }

    companion object {
        const val BUNDLE_KEY_SELECTED_PLACE_WEATHER = "selected_place_weather"
        const val BUNDLE_KEY_DATE = "date"
    }
}
