package com.example.myweather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.R
import com.example.myweather.databinding.FragmentMainBinding
import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.presentation.adapter.DailyForecastAdapter
import com.example.myweather.presentation.adapter.HourForecastAdapter
import com.example.myweather.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val args: MainFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        initObservers()
        with(viewModel) {
            getWeatherForCity(args.cityName)
            getHourForecast(args.cityName)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.weather.collect {
                        if (it != null) {
                            updateScreen(it)
                            viewModel.getWeekForecast(it.coord.lon, it.coord.lat)
                        }
                    }
                }

                launch {
                    viewModel.forecast.collect {
                        binding.rvHourForecast.apply {
                            adapter = it?.let {
                                HourForecastAdapter(it, context)
                            }
                            layoutManager = LinearLayoutManager(context).apply {
                                orientation = RecyclerView.HORIZONTAL
                            }
                        }
                    }
                }

                launch {
                    viewModel.weekForecast.collect {
                        binding.rvDailyForecast.apply {
                            adapter = it?.let {
                                DailyForecastAdapter(it, context)
                            }
                            layoutManager = LinearLayoutManager(context).apply {
                                orientation = RecyclerView.VERTICAL
                            }
                        }
                    }
                }
            }
        }
    }

    private fun updateScreen(weather: CurrentWeather?) {
        val calendar = Calendar.getInstance()
        with(binding) {
            tvCityName.text = weather?.name
            ivClouds.visibility = View.VISIBLE
            ivHumidity.visibility = View.VISIBLE
            ivWind.visibility = View.VISIBLE
            ivPressure.visibility = View.VISIBLE
            ivDirection.visibility = View.VISIBLE
            pbLoading.visibility = View.GONE
            tvMonthNumber.text = String.format(
                getString(R.string.calendar),
                calendar[Calendar.DAY_OF_MONTH],
                setMonth(calendar[Calendar.MONTH]),
                calendar[Calendar.YEAR]
            )
            tvWeekForecast.text = getString(R.string.week_forecast)
            tvTemperature.text = String.format(
                getString(R.string.temp),
                weather?.main?.temp?.toInt().toString()
            )
            tvMinTemperature.text = String.format(
                getString(R.string.min_temp),
                weather?.main?.tempMin?.toInt().toString()
            )
            tvMaxTemperature.text = String.format(
                getString(R.string.max_temp),
                weather?.main?.tempMax?.toInt().toString()
            )
            tvWind.text = String.format(
                getString(R.string.wind),
                weather?.wind?.speed?.toInt().toString()
            )
            tvHumbidity.text = String.format(
                getString(R.string.humidity),
                weather?.main?.humidity.toString()
            )
            tvPressure.text = String.format(
                getString(R.string.pressure),
                weather?.main?.pressure.toString()
            )
            tvClouds.text = String.format(
                getString(R.string.clouds),
                weather?.clouds?.all.toString()
            )
            tvDirection.text = String.format(
                getString(R.string.direction),
                setDirection(weather?.wind?.deg)
            )
            btnSearch.setOnClickListener {
                activity?.findNavController(R.id.nav_host)
                    ?.navigate(MainFragmentDirections.actionMainDestToChooseCityDest(search = true))
            }
        }
    }

    private fun setMonth(i: Int): String =
        when (i) {
            0 -> "January"
            1 -> "February"
            2 -> "March"
            3 -> "April"
            4 -> "May"
            5 -> "June"
            6 -> "July"
            7 -> "August"
            8 -> "September"
            9 -> "October"
            10 -> "November"
            else -> "December"
        }

    private fun setDirection(deg: Int?): String =
        when (deg) {
            in (0..22) -> "N"
            in (23..67) -> "NE"
            in (68..112) -> "E"
            in (113..157) -> "SE"
            in (158..202) -> "S"
            in (203..247) -> "SW"
            in (248..292) -> "S"
            in (293..337) -> "NW"
            else -> "N"
        }

}