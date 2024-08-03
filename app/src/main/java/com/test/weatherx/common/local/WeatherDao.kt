package com.test.weatherx.common.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.weatherx.common.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<WeatherEntity>)

    @Query("SELECT * FROM weather WHERE saved = 1")
    suspend fun getAll(): List<WeatherEntity>

    @Query("SELECT * FROM weather WHERE cityName = :cityName")
    suspend fun getCity(cityName: String): List<WeatherEntity>

}