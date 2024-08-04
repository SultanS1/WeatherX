package com.test.weatherx.selected.forecast.domain.usecase

import com.test.weatherx.selected.forecast.domain.model.ForecastModel
import com.test.weatherx.selected.forecast.domain.repository.ForecastRepository

class GetForeCastUseCase(private val repository: ForecastRepository) {

    suspend operator fun invoke(cityName: String): List<ForecastModel>? = repository.getForecast(cityName)

}