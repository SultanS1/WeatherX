package com.test.weatherx.common.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.weatherx.common.entity.WeatherEntity

@Dao
interface WeatherDao {

    @Insert
    suspend fun save(entity: WeatherEntity)

    @Query("SELECT * FROM weather")
    suspend fun getAll(): List<WeatherEntity>

}