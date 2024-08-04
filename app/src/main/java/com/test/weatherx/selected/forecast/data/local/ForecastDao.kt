package com.test.weatherx.selected.forecast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.weatherx.selected.forecast.data.entity.ForecastEntity

@Dao
interface ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecast: List<ForecastEntity>)

    @Query("SELECT * FROM forecast WHERE cityName = :cityName")
    suspend fun getForecast(cityName: String): List<ForecastEntity>

}