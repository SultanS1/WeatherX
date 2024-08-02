package com.test.weatherx.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = [], version = 1)
abstract class AppDatabase : RoomDatabase() {

//    abstract fun movieDao(): MoviesDao
//
//    abstract fun charactersDao(): CharactersDao
//
//    abstract fun planetDao(): PlanetDao
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