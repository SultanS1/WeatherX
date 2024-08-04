package com.test.weatherx.selected.forecast.data.repository

import com.test.weatherx.selected.forecast.data.entity.ForecastEntity
import com.test.weatherx.selected.forecast.data.entity.toDomain
import com.test.weatherx.selected.forecast.data.local.ForecastDao
import com.test.weatherx.selected.forecast.data.remote.ForecastApi
import com.test.weatherx.selected.forecast.domain.model.ForecastModel
import com.test.weatherx.selected.forecast.domain.repository.ForecastRepository

class ForecastRepositoryImpl(
    private val api: ForecastApi,
    private val db: ForecastDao
): ForecastRepository {

    override suspend fun getForecast(cityName: String): List<ForecastModel>? {
        return try {
            val apiResponse = api.getForecast(cityName).body()
            apiResponse?.let {
                val forecastList = it.forecast.forecastday.map { forecastDay ->
                    ForecastEntity(
                        cityName = it.location.name,
                        date = forecastDay.date,
                        minTemp = forecastDay.day.mintemp_c.toString(),
                        maxTemp = forecastDay.day.maxtemp_c.toString(),
                        typeDescription = forecastDay.day.condition.text,
                        typeIcon = forecastDay.day.condition.icon
                    )
                }
                db.insertForecast(forecastList)
                db.getForecast(cityName).map { it.toDomain() }
            }
        } catch (e: Exception) {
            db.getForecast(cityName).map { it.toDomain() }
        }
    }

}