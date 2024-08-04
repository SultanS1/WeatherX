package com.test.weatherx.application

import android.app.Application
import com.test.weatherx.cities.di.citiesModule
import com.test.weatherx.core.di.appModule
import com.test.weatherx.selected.forecast.di.forecastModule
import com.test.weatherx.selected.mainInfo.di.currentWeatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin() {
            androidContext(this@MainApplication)
            modules(listOf(appModule, citiesModule, currentWeatherModule, forecastModule))
        }
    }

}