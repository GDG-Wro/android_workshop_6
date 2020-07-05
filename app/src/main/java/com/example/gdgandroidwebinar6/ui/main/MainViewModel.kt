package com.example.gdgandroidwebinar6.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdgandroidwebinar6.domain.WeatherRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _models = MutableStateFlow(MainUiModel())
    val models: StateFlow<MainUiModel> = _models

    init {
        viewModelScope.launch {
            weatherRepository.getForecast()
                .onStart { if (!weatherRepository.hasForecast()) fetchForecastAsync() }
                .collect { _models.value = models.value.copy(forecasts = it) }
        }
    }

    fun fetchForecastAsync() = viewModelScope.async {
        _models.value = models.value.copy(isLoading = true)
        val isSuccessful = weatherRepository.fetchForecast()
        _models.value = models.value.copy(isLoading = false)
        return@async isSuccessful
    }
}
