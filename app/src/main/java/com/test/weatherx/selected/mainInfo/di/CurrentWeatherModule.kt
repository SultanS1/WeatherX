package com.test.weatherx.selected.mainInfo.di

import com.test.weatherx.cities.di.getWeatherApi
import com.test.weatherx.core.db.AppDatabase
import com.test.weatherx.selected.mainInfo.data.repository.CurrentDayRepositoryImpl
import com.test.weatherx.selected.mainInfo.domain.repository.CurrentDayRepository
import com.test.weatherx.selected.mainInfo.domain.usecase.GetAllLocationsUseCase
import com.test.weatherx.selected.mainInfo.domain.usecase.SaveLocationUseCase
import com.test.weatherx.selected.mainInfo.presentation.CurrentWeatherViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val currentWeatherModule = module {
    single<CurrentDayRepository> {
        CurrentDayRepositoryImpl(
            getWeatherApi(get()),
            AppDatabase.getDatabase(androidContext()).weatherDao()
        )
    }
    single { GetAllLocationsUseCase(get<CurrentDayRepository>()) }
    single { SaveLocationUseCase( get<CurrentDayRepository>()) }

    viewModel { CurrentWeatherViewModel(get(), get()) }
}