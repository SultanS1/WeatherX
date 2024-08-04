package com.test.weatherx.selected.forecast.domain.model

data class ForecastModel(
    val cityName: String,
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val typeDescription: String,
    val typeIcon: String
)
