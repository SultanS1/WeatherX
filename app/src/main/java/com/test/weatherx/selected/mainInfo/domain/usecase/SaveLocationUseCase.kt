package com.test.weatherx.selected.mainInfo.domain.usecase

import com.test.weatherx.selected.mainInfo.domain.model.CurrentWeatherModel
import com.test.weatherx.selected.mainInfo.domain.repository.CurrentDayRepository

class SaveLocationUseCase(private val repository: CurrentDayRepository) {

    suspend operator fun invoke(location: CurrentWeatherModel) = repository.saveLocation(location)

}