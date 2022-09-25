package com.example.myweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.model.Forecast
import com.example.myweather.domain.model.WeekForecast
import com.example.myweather.domain.usecase.GetHourForecastUseCase
import com.example.myweather.domain.usecase.GetWeatherUseCase
import com.example.myweather.domain.usecase.GetWeekForecastUseCase
import com.example.myweather.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getHourForecastUseCase: GetHourForecastUseCase,
    private val getWeekForecastUseCase: GetWeekForecastUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<CurrentWeather?>(null)
    val weather = _weather.asStateFlow()

    private val _forecast = MutableStateFlow<Forecast?>(null)
    val forecast = _forecast.asStateFlow()

    private val _weekForecast = MutableStateFlow<WeekForecast?>(null)
    val weekForecast = _weekForecast.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun getWeatherForCity(city: String) {
        viewModelScope.launch {
            when (val result = getWeatherUseCase(city)) {
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Success -> {
                    _weather.value = result.data
                }
            }
        }
    }

    fun getHourForecast(city: String) {
        viewModelScope.launch {
            when (val result = getHourForecastUseCase(city)) {
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Success -> {
                    _forecast.value = result.data
                }
            }
        }
    }

    fun getWeekForecast(
        lon: Double?,
        lat: Double?
    ) {
        viewModelScope.launch {
            when (val result = getWeekForecastUseCase(lon, lat)) {
                is Result.Error -> {
                    _error.value = result.message
                }
                is Result.Success -> {
                    _weekForecast.value = result.data
                }
            }
        }
    }
}
