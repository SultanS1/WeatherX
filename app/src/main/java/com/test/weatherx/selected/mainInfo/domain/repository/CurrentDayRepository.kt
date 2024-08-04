package com.test.weatherx.selected.mainInfo.domain.repository

import com.test.weatherx.selected.mainInfo.domain.model.CurrentWeatherModel

interface CurrentDayRepository {

    suspend fun saveLocation(location: CurrentWeatherModel)

    suspend fun getAllLocations(): List<CurrentWeatherModel>

}