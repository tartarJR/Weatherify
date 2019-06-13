package com.tatar.weatherify.di.detail

import com.tatar.weatherify.ui.detail.PlaceAdapter
import dagger.Module
import dagger.Provides

@Module
object DetailModule {

    @JvmStatic
    @PerDetail
    @Provides
    fun repoAdapter(itemClickListener: PlaceAdapter.ItemClickListener): PlaceAdapter {
        return PlaceAdapter(itemClickListener)
    }
}