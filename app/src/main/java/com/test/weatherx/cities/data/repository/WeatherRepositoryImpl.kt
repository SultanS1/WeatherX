package com.test.weatherx.cities.data.repository

import com.test.weatherx.cities.domain.model.WeatherModel
import com.test.weatherx.cities.domain.repository.WeatherRepository

class WeatherRepositoryImpl: WeatherRepository {

    override suspend fun getWeatherInfo(cityName: String): WeatherModel {

    }

    override suspend fun getAllLocations(): List<WeatherModel> {
        TODO("Not yet implemented")
    }
}