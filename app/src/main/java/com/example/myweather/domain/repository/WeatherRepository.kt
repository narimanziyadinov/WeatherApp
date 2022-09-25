package com.example.myweather.domain.repository

import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.model.Forecast
import com.example.myweather.domain.model.WeekForecast
import com.example.myweather.util.Result

interface WeatherRepository {

    suspend fun getWeather(city: String): Result<CurrentWeather>

    suspend fun getWeatherWithLocation(
        lon: Double?,
        lat: Double?
    ): Result<CurrentWeather>

    suspend fun getHourForecast(city: String): Result<Forecast>

    suspend fun getWeekForecast(
        lon: Double?,
        lat: Double?
    ): Result<WeekForecast>
}
