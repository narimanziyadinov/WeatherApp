package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.Forecast
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.util.Result
import javax.inject.Inject

class GetHourForecastUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(city: String): Result<Forecast> {
        return repository.getHourForecast(city)
    }
}
