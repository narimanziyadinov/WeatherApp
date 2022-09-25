package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.repository.LocalRepository
import javax.inject.Inject

class ChooseCityUseCase @Inject constructor(
    private val repository: LocalRepository,
) {

    suspend operator fun invoke(weather: CurrentWeather) = repository.addCityWeather(weather)
}
