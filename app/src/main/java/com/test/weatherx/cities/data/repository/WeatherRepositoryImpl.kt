package com.test.weatherx.cities.data.repository

import com.test.weatherx.cities.domain.model.WeatherModel
import com.test.weatherx.cities.domain.repository.WeatherRepository
import com.test.weatherx.common.data.entity.WeatherEntity
import com.test.weatherx.common.data.entity.toDomainModel
import com.test.weatherx.common.data.local.WeatherDao
import com.test.weatherx.common.data.remote.WeatherApi

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val db: WeatherDao
): WeatherRepository {

    override suspend fun getWeatherInfo(cityName: String): WeatherModel? {
        return try {
            val apiResponse = api.getCityInfo(cityName).body()
            apiResponse?.let {
                val weatherEntity = WeatherEntity(
                    cityName = it.location.name,
                    countryName = it.location.country,
                    dateTime = it.location.localtime,
                    temp = it.current.temp_c.toString(),
                    tempH = it.current.heatindex_c.toString(),
                    tempM = it.current.dewpoint_c.toString(),
                    typeDescription = it.current.condition.text,
                    typeIcon = it.current.condition.icon,
                    windSpeed = it.current.wind_kph.toString(),
                    humidity = it.current.humidity.toString(),
                    saved = false
                )
                db.save(weatherEntity)
                db.getCity(cityName).firstOrNull()?.toDomainModel()
            }
        } catch (e: Exception) {
            db.getCity(cityName).firstOrNull()?.toDomainModel()
        }
    }

    override suspend fun getAllLocations(): List<WeatherModel> {
        val refreshedCities = mutableListOf<WeatherEntity>()
        val cities = db.getAll()
        cities.forEach { city ->
            try {
                val apiResponse = api.getCityInfo(city.cityName).body()
                apiResponse?.let {
                    val weatherEntity = WeatherEntity(
                        cityName = it.location.name,
                        countryName = it.location.country,
                        dateTime = it.location.localtime,
                        temp = it.current.temp_c.toString(),
                        tempH = it.current.feelslike_c.toString(),
                        tempM = it.current.temp_c.toString(),
                        typeDescription = it.current.condition.text,
                        typeIcon = it.current.condition.icon,
                        windSpeed = it.current.wind_kph.toString(),
                        humidity = it.current.humidity.toString(),
                        saved = true
                    )
                    refreshedCities.add(weatherEntity)
                }
            } catch (_: Exception) {
            }
        }

        if (refreshedCities.isNotEmpty()) {
            db.insertCities(refreshedCities)
        }

        return db.getAll().map { it.toDomainModel() }
    }
}