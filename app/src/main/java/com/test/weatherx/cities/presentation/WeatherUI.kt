package com.test.weatherx.cities.presentation

data class WeatherUI(
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
