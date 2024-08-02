package com.test.weatherx.cities.domain.usecase

import com.test.weatherx.cities.domain.model.WeatherModel
import com.test.weatherx.cities.domain.repository.WeatherRepository

class GetSearchResultUseCase(private val repository: WeatherRepository) {

    suspend operator fun invoke(cityName: String): WeatherModel = repository.getWeatherInfo(cityName)

}