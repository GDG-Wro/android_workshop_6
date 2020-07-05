package com.example.gdgandroidwebinar6.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gdgandroidwebinar6.Consumable
import com.example.gdgandroidwebinar6.domain.WeatherRepository
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
                .onStart { if (!weatherRepository.hasForecast()) fetchForecast() }
                .collect { _models.value = models.value.copy(forecasts = it) }
        }
    }

    fun fetchForecast() {
        viewModelScope.launch {
            _models.value = models.value.copy(isLoading = true)
            val isSuccessful = weatherRepository.fetchForecast()
            val error = Consumable(if (isSuccessful) null else Unit)
            _models.value = models.value.copy(isLoading = false, error = error)
        }
    }
}
