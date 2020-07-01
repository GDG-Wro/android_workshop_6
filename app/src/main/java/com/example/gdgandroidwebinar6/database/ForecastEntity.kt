package com.example.gdgandroidwebinar6.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.threeten.bp.LocalDate

@Entity(tableName = "forecast")
data class ForecastEntity(
    @PrimaryKey
    val date: LocalDate,
    val description: String,
    val state: String,
    val temperature: Double,
    val maxTemperature: Double,
    val minTemperature: Double
)

