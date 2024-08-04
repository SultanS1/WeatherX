package com.test.weatherx.selected.forecast.di

import com.test.weatherx.core.db.AppDatabase
import com.test.weatherx.selected.forecast.data.remote.ForecastApi
import com.test.weatherx.selected.forecast.data.repository.ForecastRepositoryImpl
import com.test.weatherx.selected.forecast.domain.repository.ForecastRepository
import com.test.weatherx.selected.forecast.domain.usecase.GetForeCastUseCase
import com.test.weatherx.selected.forecast.presentation.ForecastViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val forecastModule = module {

    single<ForecastRepository> {
        ForecastRepositoryImpl(
            getForecastApi(get()),
            AppDatabase.getDatabase(androidContext()).forecastDao()
        )
    }
    single { GetForeCastUseCase(get<ForecastRepository>()) }

    viewModel { ForecastViewModel(get()) }

}

fun getForecastApi(retrofit: Retrofit): ForecastApi {
    return retrofit.create(ForecastApi::class.java)
}