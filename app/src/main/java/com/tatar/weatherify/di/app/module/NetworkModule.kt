package com.tatar.weatherify.di.app.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tatar.weatherify.data.network.WeatherApi
import com.tatar.weatherify.di.PerApp
import com.tatar.weatherify.util.NetworkUtil
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File

@Module
object NetworkModule {

    @PerApp
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(WeatherApi.BASE_URL)
            .build()
    }

    @PerApp
    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder().setDateFormat("yyyy-MM-dd")
        return gsonBuilder.create()
    }

    @PerApp
    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

    @PerApp
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        })

        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        return interceptor
    }

    @PerApp
    @Provides
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000)
    }

    @PerApp
    @Provides
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "network_cache")
    }

    @PerApp
    @Provides
    fun networkUtil(context: Context): NetworkUtil {
        return NetworkUtil(context)
    }
}