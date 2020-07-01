package com.example.gdgandroidwebinar6.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gdgandroidwebinar6.api.LocalDateAdapter
import com.example.gdgandroidwebinar6.api.WeatherService
import com.example.gdgandroidwebinar6.database.WeatherDatabase
import com.example.gdgandroidwebinar6.domain.WeatherRepository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(MainViewModel::class.java)) {
            error("This factory can instantiate only ${MainViewModel::class.java}, but requested $modelClass")
        }
        val weatherRepository = createWeatherRepository(context)
        return MainViewModel(weatherRepository) as T
    }

    private fun createWeatherRepository(context: Context): WeatherRepository {
        val weatherService = createWeatherService()
        val weatherDao = WeatherDatabase.getWeatherDao(context)
        return WeatherRepository(weatherService, weatherDao)
    }

    private fun createWeatherService(): WeatherService {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val moshi = Moshi.Builder()
            .add(LocalDateAdapter)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://www.metaweather.com/")
            .client(client)
            .build()
            .create()
    }
}

