package com.test.weatherx.selected.mainInfo.domain.model

data class CurrentWeatherModel(
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
