package com.test.weatherx.cities.domain.usecase

import com.test.weatherx.cities.domain.model.WeatherModel
import com.test.weatherx.cities.domain.repository.WeatherRepository

class GetLatestSearches(private val repository: WeatherRepository) {

    suspend operator fun invoke(): List<WeatherModel> = repository.getAllLocations()

}