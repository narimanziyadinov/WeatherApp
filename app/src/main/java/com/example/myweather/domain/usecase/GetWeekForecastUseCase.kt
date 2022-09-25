package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.WeekForecast
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.util.Result
import javax.inject.Inject

class GetWeekForecastUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(
        lon: Double?,
        lat: Double?
    ): Result<WeekForecast> {
        return repository.getWeekForecast(lon, lat)
    }
}
