package com.test.weatherx.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.weatherx.common.data.entity.WeatherEntity
import com.test.weatherx.common.data.local.WeatherDao
import com.test.weatherx.selected.forecast.data.entity.ForecastEntity
import com.test.weatherx.selected.forecast.data.local.ForecastDao

@Database(entities = [WeatherEntity::class, ForecastEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
    abstract fun forecastDao(): ForecastDao
    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Database").build()
                INSTANCE = instance
                return instance
            }
        }

    }

}