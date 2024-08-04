package com.test.weatherx.cities.domain.repository

import com.test.weatherx.cities.domain.model.WeatherModel

interface WeatherRepository {

    suspend fun getWeatherInfo(cityName: String): WeatherModel?

    suspend fun getAllLocations(): List<WeatherModel>

}