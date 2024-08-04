package com.test.weatherx.common.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.weatherx.cities.domain.model.WeatherModel
import com.test.weatherx.selected.mainInfo.domain.model.CurrentWeatherModel

@Entity("weather")
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(index = true)
    val cityName: String,
    val countryName: String,
    val dateTime: String,
    val temp: String,
    val tempH: String,
    val tempM: String,
    val typeDescription: String,
    val typeIcon: String,
    val windSpeed: String,
    val humidity: String,
    val saved: Boolean = false
)

fun WeatherEntity.toDomainModel(): WeatherModel {
    return WeatherModel(
        cityName = cityName,
        countryName = countryName,
        dateTime = dateTime,
        temp = "$temp°",
        tempH = "$tempH°",
        tempM = "$tempM°",
        typeDescription = typeDescription,
        typeIcon = typeIcon,
        windSpeed = "$windSpeed km/h",
        humidity = "$humidity %",
        saved = saved
    )
}

fun WeatherEntity.toDomainWeatherModel(): CurrentWeatherModel {
    return CurrentWeatherModel(
        cityName = cityName,
        countryName = countryName,
        dateTime = dateTime,
        temp = "$temp°",
        tempH = "$tempH°",
        tempM = "$tempM°",
        typeDescription = typeDescription,
        typeIcon = typeIcon,
        windSpeed = "$windSpeed km/h",
        humidity = "$humidity %",
        saved = saved
    )
}

fun CurrentWeatherModel.toData(): WeatherEntity{
    return WeatherEntity(
        cityName = cityName,
        countryName = countryName,
        dateTime = dateTime,
        temp = "$temp°",
        tempH = "$tempH°",
        tempM = "$tempM°",
        typeDescription = typeDescription,
        typeIcon = typeIcon,
        windSpeed = "$windSpeed km/h",
        humidity = "$humidity %",
        saved = saved
    )
}


