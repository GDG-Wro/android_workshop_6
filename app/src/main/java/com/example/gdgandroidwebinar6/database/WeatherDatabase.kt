package com.example.gdgandroidwebinar6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ForecastEntity::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun dao(): WeatherDao

    companion object {
        private lateinit var applicationContext: Context

        private val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                WeatherDatabase::class.java,
                "weather.db"
            ).build()
        }

        fun getWeatherDao(context: Context): WeatherDao {
            applicationContext = context.applicationContext
            return database.dao()
        }
    }
}
