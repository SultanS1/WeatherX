package com.test.weatherx.selected.mainInfo.presentation

import android.os.Parcelable
import com.test.weatherx.selected.mainInfo.domain.model.CurrentWeatherModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeatherUI(
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
) : Parcelable

fun CurrentWeatherModel.toUI(): CurrentWeatherUI {
    return CurrentWeatherUI(
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

fun CurrentWeatherUI.toModel(): CurrentWeatherModel {
    return CurrentWeatherModel(
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