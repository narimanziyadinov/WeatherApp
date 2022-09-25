package com.example.myweather.domain.repository

import com.example.myweather.domain.model.CurrentWeather

interface LocalRepository {

    suspend fun addCityWeather(weather: CurrentWeather)

    suspend fun getLastCity(): String?
}
