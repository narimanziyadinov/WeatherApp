package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.util.Result
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(city: String): Result<CurrentWeather> {
        return repository.getWeather(city)
    }
}
