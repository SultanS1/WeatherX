package com.test.weatherx.selected.mainInfo.domain.usecase

import com.test.weatherx.selected.mainInfo.domain.model.CurrentWeatherModel
import com.test.weatherx.selected.mainInfo.domain.repository.CurrentDayRepository

class GetAllLocationsUseCase(private val repository: CurrentDayRepository) {

    suspend operator fun invoke(): List<CurrentWeatherModel> = repository.getAllLocations()

}