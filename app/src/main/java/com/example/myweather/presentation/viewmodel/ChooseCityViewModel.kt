package com.example.myweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.usecase.ChooseCityUseCase
import com.example.myweather.domain.usecase.GetLastCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChooseCityViewModel @Inject constructor(
    private val chooseCityUseCase: ChooseCityUseCase,
    private val getLastCityUseCase: GetLastCityUseCase
) : ViewModel() {

    private val _lastCity = MutableStateFlow<String?>(null)
    val lastCity = _lastCity.asStateFlow()

    fun chooseCity(weather: CurrentWeather) {
        viewModelScope.launch {
            chooseCityUseCase(weather)
        }
    }

    fun checkCity() {
        viewModelScope.launch {
            _lastCity.value = getLastCityUseCase()
        }
    }
}