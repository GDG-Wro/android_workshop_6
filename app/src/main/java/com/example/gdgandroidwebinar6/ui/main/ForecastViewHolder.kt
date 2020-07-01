package com.example.gdgandroidwebinar6.ui.main

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.gdgandroidwebinar6.R
import com.example.gdgandroidwebinar6.domain.Forecast
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.forecast_item.view.*
import org.threeten.bp.format.DateTimeFormatter

class ForecastViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {
    fun bind(forecast: Forecast) = with(containerView) {
        icon.load(forecast.icon) {
            error(R.drawable.ic_warning)
        }
        date.text = forecast.date.format(DateTimeFormatter.ofPattern("EEEE, d LLL yyyy"))
        temperature.text = context.getString(R.string.degree_format, forecast.temperature)
        description.text = forecast.description
        temperatureRange.text = context.getString(
            R.string.degree_range_format,
            forecast.minTemperature,
            forecast.maxTemperature
        )
    }
}
