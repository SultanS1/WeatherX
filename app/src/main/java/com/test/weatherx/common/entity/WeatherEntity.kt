package com.test.weatherx.common.entity

import androidx.room.Entity
import com.test.weatherx.cities.domain.model.WeatherModel

@Entity("weather")
data class WeatherEntity(
    val cityName: String,
    val countryName: String,
    val dateTime: String,
    val temp: Double,
    val tempH: Double,
    val tempM: Double,
    val typeDescription: String,
    val typeIcon: String,
    val windSpeed: Double,
    val humidity: Int
)

fun WeatherEntity.toDomainModel(): WeatherModel {
    return WeatherModel(
        cityName = cityName,
        countryName = countryName,
        dateTime = dateTime,
        temp = "$temp °",
        tempH = "$tempH °",
        tempM = "$tempM °",
        typeDescription = typeDescription,
        typeIcon = typeIcon,
        windSpeed = "$windSpeed km/h",
        humidity = "$humidity %"
    )
}


