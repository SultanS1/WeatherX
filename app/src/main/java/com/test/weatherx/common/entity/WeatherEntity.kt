package com.test.weatherx.common.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.weatherx.cities.domain.model.WeatherModel

@Entity("weather")
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(index = true)
    val cityName: String,
    val countryName: String,
    val dateTime: String,
    val temp: Double,
    val tempH: Double,
    val tempM: Double,
    val typeDescription: String,
    val typeIcon: String,
    val windSpeed: Double,
    val humidity: Int,
    val saved: Boolean = false
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
        humidity = "$humidity %",
        saved = saved
    )
}


