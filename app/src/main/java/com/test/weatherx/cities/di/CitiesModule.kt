package com.test.weatherx.cities.di

import com.test.weatherx.cities.data.repository.WeatherRepositoryImpl
import com.test.weatherx.cities.domain.repository.WeatherRepository
import com.test.weatherx.cities.domain.usecase.GetSavedLocationsUseCase
import com.test.weatherx.cities.domain.usecase.GetSearchResultUseCase
import com.test.weatherx.cities.presentation.CitiesViewModel
import com.test.weatherx.common.remote.WeatherApi
import com.test.weatherx.core.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val citiesModule = module {

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            getWeatherApi(get()),
            AppDatabase.getDatabase(androidContext()).weatherDao()
        )
    }
    single { GetSearchResultUseCase(get<WeatherRepository>()) }
    single { GetSavedLocationsUseCase( get<WeatherRepository>()) }

    viewModel { CitiesViewModel(get(), get()) }

}

fun getWeatherApi(retrofit: Retrofit): WeatherApi {
    return retrofit.create(WeatherApi::class.java)
}