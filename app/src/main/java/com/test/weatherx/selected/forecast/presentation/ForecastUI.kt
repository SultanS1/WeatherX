package com.test.weatherx.selected.forecast.presentation

import com.test.weatherx.selected.forecast.domain.model.ForecastModel

data class ForecastUI(
    val cityName: String,
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val typeDescription: String,
    val typeIcon: String
)


fun ForecastModel.toUI(): ForecastUI{
    return ForecastUI(
        cityName = cityName,
        date = date,
        minTemp = minTemp,
        maxTemp = maxTemp,
        typeDescription = typeDescription,
        typeIcon = typeIcon
    )
}
