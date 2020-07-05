package com.example.gdgandroidwebinar6.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateAdapter {
    private val apiDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    @FromJson
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value, apiDateFormatter)
    }

    @ToJson
    fun fromLocalDate(value: LocalDate): String = value.format(apiDateFormatter)
}
