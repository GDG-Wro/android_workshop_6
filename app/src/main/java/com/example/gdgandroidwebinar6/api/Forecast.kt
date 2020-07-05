package com.example.gdgandroidwebinar6.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate

@JsonClass(generateAdapter = true)
data class Forecast(
    @Json(name = "applicable_date") val date: LocalDate,
    @Json(name = "weather_state_name") val description: String,
    @Json(name = "weather_state_abbr") val state: String,
    @Json(name = "the_temp") val temperature: Double,
    @Json(name = "max_temp") val maxTemperature: Double,
    @Json(name = "min_temp") val minTemperature: Double
)
