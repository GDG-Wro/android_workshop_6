package com.example.gdgandroidwebinar6.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdgandroidwebinar6.R
import com.example.gdgandroidwebinar6.domain.Forecast
import kotlinx.android.synthetic.main.main_fragment.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.threeten.bp.LocalDate

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherRecyclerView.apply {
            val weatherAdapter = WeatherAdapter()
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            weatherAdapter.submitList(
                listOf(
                    Forecast(
                        date = LocalDate.now(),
                        description = "Test description",
                        icon = "https://www.metaweather.com/static/img/weather/png/s.png".toHttpUrl(),
                        temperature = 10.2,
                        maxTemperature = 11.0,
                        minTemperature = 8.1
                    ),
                    Forecast(
                        date = LocalDate.now().plusDays(1),
                        description = "Test description 2",
                        icon = "https://www.metaweather.com/static/img/weather/png/t.png".toHttpUrl(),
                        temperature = 5.2,
                        maxTemperature = 5.0,
                        minTemperature = 2.1
                    )
                )
            )
        }
    }
}
