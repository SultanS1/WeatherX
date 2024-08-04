package com.test.weatherx.selected.forecast.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.test.weatherx.selected.forecast.domain.model.ForecastModel

@Entity(
    tableName = "forecast",
    indices = [Index(value = ["date"], unique = true)]
)
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val cityName: String,
    val date: String,
    val minTemp: String,
    val maxTemp: String,
    val typeDescription: String,
    val typeIcon: String
)


fun ForecastEntity.toDomain(): ForecastModel{
    return ForecastModel(
        cityName = cityName,
        date = date,
        minTemp = minTemp,
        maxTemp = maxTemp,
        typeDescription = typeDescription,
        typeIcon = typeIcon
    )
}