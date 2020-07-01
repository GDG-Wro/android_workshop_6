package com.example.gdgandroidwebinar6.domain

import okhttp3.HttpUrl
import org.threeten.bp.LocalDate

data class Forecast(
    val date: LocalDate,
    val description: String,
    val icon: HttpUrl,
    val temperature: Double,
    val maxTemperature: Double,
    val minTemperature: Double
)
