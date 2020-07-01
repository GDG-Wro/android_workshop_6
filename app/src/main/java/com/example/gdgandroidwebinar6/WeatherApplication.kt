package com.example.gdgandroidwebinar6

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class WeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
