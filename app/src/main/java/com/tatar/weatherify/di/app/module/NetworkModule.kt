package com.tatar.weatherify.di.app.module

import android.content.Context
import com.tatar.weatherify.di.app.scope.PerApp
import com.tatar.weatherify.util.NetworkUtil
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

@Module
object NetworkModule {

    @JvmStatic
    @PerApp
    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

    @JvmStatic
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

    @JvmStatic
    @PerApp
    @Provides
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000)
    }

    @JvmStatic
    @PerApp
    @Provides
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "network_cache")
    }

    @JvmStatic
    @PerApp
    @Provides
    fun networkUtil(context: Context): NetworkUtil {
        return NetworkUtil(context)
    }
}