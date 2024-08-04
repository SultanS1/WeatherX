package com.test.weatherx.cities.presentation

import com.test.weatherx.cities.domain.model.WeatherModel

data class WeatherUI(
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
    val saved: Boolean
)

fun WeatherModel.toUI(): WeatherUI{
    return WeatherUI(
        cityName = cityName,
        countryName = countryName,
        dateTime = dateTime,
        temp = temp,
        tempH = tempH,
        tempM = tempM,
        typeDescription = typeDescription,
        typeIcon = typeIcon,
        windSpeed = windSpeed,
        humidity = humidity,
        saved = saved
    )
}
