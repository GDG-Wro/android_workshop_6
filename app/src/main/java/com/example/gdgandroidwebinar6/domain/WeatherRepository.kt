package com.example.gdgandroidwebinar6.domain

import android.util.Log
import com.example.gdgandroidwebinar6.api.WeatherService
import com.example.gdgandroidwebinar6.database.ForecastEntity
import com.example.gdgandroidwebinar6.database.WeatherDao
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.HttpUrl.Companion.toHttpUrl
import com.example.gdgandroidwebinar6.api.Forecast as ApiForecast

class WeatherRepository(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao
) {
    fun getForecast(): Flow<List<Forecast>> {
        return weatherDao.getForecasts().map { forecasts ->
            forecasts.map { forecastEntity -> forecastEntity.toForecast() }
        }
    }

    suspend fun fetchForecast(): Boolean {
        try {
            val weather = weatherService.getWeather()
            weatherDao.replaceForecast(forecasts = weather.forecasts.map { it.toForecastEntity() })
            return true
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e("API", "Fetching forecast failed", e)
        }
        return false
    }

    suspend fun hasForecast() = weatherDao.getForecastCount() > 0L
}

private fun ApiForecast.toForecastEntity(): ForecastEntity {
    return ForecastEntity(
        date = date,
        description = description,
        state = state,
        temperature = temperature,
        maxTemperature = maxTemperature,
        minTemperature = minTemperature
    )
}

private fun ForecastEntity.toForecast(): Forecast {
    return Forecast(
        date = date,
        description = description,
        icon = "https://www.metaweather.com/static/img/weather/png/$state.png".toHttpUrl(),
        temperature = temperature,
        maxTemperature = maxTemperature,
        minTemperature = minTemperature
    )
}
