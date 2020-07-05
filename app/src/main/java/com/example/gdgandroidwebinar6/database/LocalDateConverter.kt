package com.example.gdgandroidwebinar6.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun toLocalDate(value: Long): LocalDate = LocalDate.ofEpochDay(value)

    @TypeConverter
    fun fromLocalDate(value: LocalDate): Long = value.toEpochDay()
}
