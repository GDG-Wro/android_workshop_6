package com.example.gdgandroidwebinar6.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdgandroidwebinar6.domain.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    fun fetchForecastAsync() = viewModelScope.async {
        weatherRepository.fetchForecast()
    }

    fun getModels() = weatherRepository.getForecast()
        .onStart { if (!weatherRepository.hasForecast()) fetchForecastAsync() }
        .map { MainUiModel(forecasts = it) }
}
