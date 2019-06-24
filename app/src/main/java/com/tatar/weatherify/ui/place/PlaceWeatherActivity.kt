package com.tatar.weatherify.ui.place

import com.tatar.weatherify.App
import com.tatar.weatherify.R
import com.tatar.weatherify.data.network.model.Place
import com.tatar.weatherify.di.place.DaggerPlaceComponent
import com.tatar.weatherify.ui.base.BaseActivity
import com.tatar.weatherify.ui.detail.DetailWeatherActivity
import kotlinx.android.synthetic.main.activity_place_weather.*
import javax.inject.Inject

class PlaceWeatherActivity : BaseActivity(), PlaceWeatherContract.View {

    @Inject
    lateinit var placeWeatherMvpPresenter: PlaceWeatherContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.activity_place_weather
    }

    override fun provideDependencies() {
        val placeComponent = DaggerPlaceComponent.builder()
            .placeWeatherActivity(this)
            .appComponent((application as App).appComponent()).build()

        placeComponent.injectPlaceWeatherActivity(this)
    }

    override fun initViews() {

    }

    override fun init() {
        placeWeatherMvpPresenter.attachView(this)

        val dateString = intent?.extras?.getString(DetailWeatherActivity.BUNDLE_KEY_DATE)
        val place = intent?.extras?.getParcelable<Place>(DetailWeatherActivity.BUNDLE_KEY_SELECTED_PLACE_WEATHER)

        placeWeatherMvpPresenter.displayPlaceWeatherInformation(dateString!!, place!!)
    }

    override fun releasePresenterResources() {
        placeWeatherMvpPresenter.detachView()
    }

    override fun displayPlaceWeatherInfo(dateString: String, place: Place) {
        place_name_tv.text = place.name
        place_weather_brief_view.setPlace(dateString, place)
    }
}
