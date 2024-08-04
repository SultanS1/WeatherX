package com.test.weatherx.selected.forecast.domain.repository

import com.test.weatherx.selected.forecast.domain.model.ForecastModel

interface ForecastRepository {

    suspend fun getForecast(cityName: String): List<ForecastModel>?

}