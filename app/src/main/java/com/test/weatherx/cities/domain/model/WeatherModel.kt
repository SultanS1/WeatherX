package com.test.weatherx.cities.domain.model

data class WeatherModel(
    val cityName: String,
    val countryName: String,
    val dateTime: String,
    val temp: String,
    val tempH: String,
    val tempM: String,
    val typeDescription: String,
    val typeIcon: String,
    val windSpeed: String,
    val humidity: String
)