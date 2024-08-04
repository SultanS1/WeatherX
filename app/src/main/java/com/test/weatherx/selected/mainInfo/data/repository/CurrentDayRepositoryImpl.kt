package com.test.weatherx.selected.mainInfo.data.repository

import com.test.weatherx.common.data.entity.WeatherEntity
import com.test.weatherx.common.data.entity.toData
import com.test.weatherx.common.data.entity.toDomainWeatherModel
import com.test.weatherx.common.data.local.WeatherDao
import com.test.weatherx.common.data.remote.WeatherApi
import com.test.weatherx.selected.mainInfo.domain.model.CurrentWeatherModel
import com.test.weatherx.selected.mainInfo.domain.repository.CurrentDayRepository

class CurrentDayRepositoryImpl(
    private val api: WeatherApi,
    private val db: WeatherDao
): CurrentDayRepository {

    override suspend fun saveLocation(location: CurrentWeatherModel) {
        db.save(location.toData())
    }

    override suspend fun getAllLocations(): List<CurrentWeatherModel> {
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

        return db.getAll().map { it.toDomainWeatherModel() }
    }
}