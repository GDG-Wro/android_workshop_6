package com.example.gdgandroidwebinar6.domain

import com.example.gdgandroidwebinar6.api.LocalDateAdapter
import com.example.gdgandroidwebinar6.api.WeatherService
import com.example.gdgandroidwebinar6.database.WeatherDao
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class WeatherRepository(private val weatherService: WeatherService, private val weatherDao: WeatherDao) {
}
