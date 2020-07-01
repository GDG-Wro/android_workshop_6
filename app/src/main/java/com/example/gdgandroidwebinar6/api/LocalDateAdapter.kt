package com.example.gdgandroidwebinar6.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate

object LocalDateAdapter {
    @FromJson
    fun toLocalDate(value: Long): LocalDate =
        LocalDate.from(
            Instant.ofEpochSecond(value)
        )

    @ToJson
    fun fromLocalDate(value: LocalDate): Long = Instant.from(
        value
    ).epochSecond
}
