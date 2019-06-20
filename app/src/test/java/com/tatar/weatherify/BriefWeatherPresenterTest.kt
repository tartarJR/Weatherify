package com.tatar.weatherify

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.data.network.model.WeatherForecastResponse
import com.tatar.weatherify.data.prefs.SharedPreferencesManager
import com.tatar.weatherify.ui.brief.BriefWeatherMvpView
import com.tatar.weatherify.ui.brief.BriefWeatherPresenter
import com.tatar.weatherify.util.NetworkUtil
import com.tatar.weatherify.util.SunriseSunsetUtil
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


class BriefWeatherPresenterTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var mockBriefWeatherMvpView: BriefWeatherMvpView

    @Mock
    private lateinit var mockWeatherApi: WeatherApi

    @Mock
    private lateinit var mockSharedPreferencesManager: SharedPreferencesManager

    @Mock
    private lateinit var mockNetworkUtil: NetworkUtil

    @Mock
    private lateinit var mockSunriseSunsetUtil: SunriseSunsetUtil

    private lateinit var briefWeatherPresenter: BriefWeatherPresenter

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        briefWeatherPresenter = BriefWeatherPresenter(
            mockWeatherApi,
            mockSharedPreferencesManager,
            mockNetworkUtil,
            mockSunriseSunsetUtil
        )

        briefWeatherPresenter.attachView(mockBriefWeatherMvpView)
    }

    @Test
    fun test_retrieveWeatherForecastInformation_day_hasConnection_should_callSuccess() {
        val mockedWeatherForecastResponse: WeatherForecastResponse = makeSuccessWeatherApiCall()

        makeFakeSunriseSunsetUtil(IS_DAY)
        makeFakeNetworkUtilCall(true)

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(IS_DAY, 1, 1)
        verify(mockBriefWeatherMvpView).showFourDaysBriefWeatherInfo(mockedWeatherForecastResponse, IS_DAY)
        verify(mockSharedPreferencesManager).saveLatestWeatherForecastData(mockedWeatherForecastResponse)
    }

    @Test
    fun test_retrieveWeatherForecastInformation_night_hasConnection_should_callSuccess() {
        val mockedWeatherForecastResponse: WeatherForecastResponse = makeSuccessWeatherApiCall()

        makeFakeSunriseSunsetUtil(NOT_IS_DAY)
        makeFakeNetworkUtilCall(true)

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(NOT_IS_DAY, 1, 1)
        verify(mockBriefWeatherMvpView).showFourDaysBriefWeatherInfo(mockedWeatherForecastResponse, NOT_IS_DAY)
        verify(mockSharedPreferencesManager).saveLatestWeatherForecastData(mockedWeatherForecastResponse)
    }

    @Test
    fun test_retrieveWeatherForecastInformation_day_hasConnection_should_callError() {
        makeErrorWeatherApiCall()

        makeFakeSunriseSunsetUtil(IS_DAY)
        makeFakeNetworkUtilCall(true)

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(IS_DAY, 2, 2)
        verify(mockBriefWeatherMvpView).displayErrorMessage()
    }

    @Test
    fun test_retrieveWeatherForecastInformation_night_hasConnection_should_callError() {
        makeErrorWeatherApiCall()

        makeFakeSunriseSunsetUtil(NOT_IS_DAY)
        makeFakeNetworkUtilCall(true)

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(NOT_IS_DAY, 2, 2)
        verify(mockBriefWeatherMvpView).displayErrorMessage()
    }

    @Test
    fun test_retrieveWeatherForecastInformation_day_noConnection_should_noCachedData() {

        makeFakeSunriseSunsetUtil(IS_DAY)
        makeFakeNetworkUtilCall(false)
        prefsCall(null)

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(IS_DAY, 2, 2)
        verify(mockBriefWeatherMvpView).displayNoInternetWarning()
    }

    @Test
    fun test_retrieveWeatherForecastInformation_night_noConnection_should_noCachedData() {
        makeFakeSunriseSunsetUtil(NOT_IS_DAY)
        makeFakeNetworkUtilCall(false)
        prefsCall(null)

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(NOT_IS_DAY, 2, 2)
        verify(mockBriefWeatherMvpView).displayNoInternetWarning()
    }

    @Test
    fun test_retrieveWeatherForecastInformation_day_noConnection_should_callCachedData() {
        makeFakeSunriseSunsetUtil(IS_DAY)
        makeFakeNetworkUtilCall(false)
        val mockedWeatherForecastResponse: WeatherForecastResponse = prefsCall(mock() as WeatherForecastResponse)!!

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(IS_DAY, 1, 1)
        verify(mockBriefWeatherMvpView).showFourDaysBriefWeatherInfo(mockedWeatherForecastResponse, IS_DAY)
        verify(mockBriefWeatherMvpView).showCachedDataDisplayedToast()
    }

    @Test
    fun test_retrieveWeatherForecastInformation_night_noConnection_should_callCachedData() {
        makeFakeSunriseSunsetUtil(NOT_IS_DAY)
        makeFakeNetworkUtilCall(false)
        val mockedWeatherForecastResponse: WeatherForecastResponse = prefsCall(mock() as WeatherForecastResponse)!!

        briefWeatherPresenter.retrieveWeatherForecastInformation()

        verifyCommonViewFunctions(NOT_IS_DAY, 1, 1)
        verify(mockBriefWeatherMvpView).showFourDaysBriefWeatherInfo(mockedWeatherForecastResponse, NOT_IS_DAY)
        verify(mockBriefWeatherMvpView).showCachedDataDisplayedToast()
    }

    private fun makeSuccessWeatherApiCall(): WeatherForecastResponse {
        val mockedWeatherForecastResponse: WeatherForecastResponse = mock()

        doReturn(Single.just(mockedWeatherForecastResponse))
            .`when`(mockWeatherApi)
            .getFourDaysWeatherForecast()

        return mockedWeatherForecastResponse
    }

    private fun makeErrorWeatherApiCall() {
        doReturn(Single.error<Throwable>(Exception()))
            .`when`(mockWeatherApi)
            .getFourDaysWeatherForecast()
    }

    private fun prefsCall(desiredPrefsResponse: WeatherForecastResponse?): WeatherForecastResponse? {
        val mockedWeatherForecastResponse: WeatherForecastResponse? = desiredPrefsResponse

        doReturn(desiredPrefsResponse)
            .`when`(mockSharedPreferencesManager)
            .getCachedWeatherForecastData()

        return mockedWeatherForecastResponse
    }

    private fun makeFakeSunriseSunsetUtil(isDayLight: Boolean) {
        doReturn(isDayLight)
            .`when`(mockSunriseSunsetUtil)
            .isDayLight()
    }

    private fun makeFakeNetworkUtilCall(hasConnection: Boolean) {
        doReturn(hasConnection)
            .`when`(mockNetworkUtil)
            .hasInternetConnection()
    }

    private fun verifyCommonViewFunctions(
        isDayLight: Boolean,
        hideFourDaysBriefWeatherInfoTimes: Int,
        showStatusTvTimes: Int
    ) {
        verify(mockSunriseSunsetUtil).isDayLight()
        if (isDayLight) verify(mockBriefWeatherMvpView).setDayBgImage() else verify(mockBriefWeatherMvpView).setNightBgImage()
        verify(mockBriefWeatherMvpView, times(hideFourDaysBriefWeatherInfoTimes)).hideFourDaysBriefWeatherInfo()
        verify(mockBriefWeatherMvpView).displayLoadingMessage()
        verify(mockBriefWeatherMvpView, times(showStatusTvTimes)).showStatusTv()
        verify(mockBriefWeatherMvpView).showProgressBar()
        verify(mockBriefWeatherMvpView).hideStatusTv()
        verify(mockBriefWeatherMvpView).hideProgressBar()
    }

    companion object {
        private const val NOT_IS_DAY = false
        private const val IS_DAY = true
    }
}
