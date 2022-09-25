package com.example.myweather.data.repository

import com.example.myweather.data.local.db.dao.CityDao
import com.example.myweather.data.retrofit.WeatherApi
import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.model.Forecast
import com.example.myweather.domain.model.WeekForecast
import com.example.myweather.domain.repository.WeatherRepository
import com.example.myweather.util.Result
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val dao: CityDao
) : WeatherRepository {

    override suspend fun getWeather(city: String): Result<CurrentWeather> {
        val resp = api.getWeather(city)
        return if (resp.isSuccessful) {
            Result.Success(data = resp.body())
        } else {
            Result.Error(resp.message())
        }
    }

    override suspend fun getWeatherWithLocation(
        lon: Double?,
        lat: Double?
    ): Result<CurrentWeather> {
        val resp = api.getWeatherWithCoordinates(lon, lat)
        return if (resp.isSuccessful) {
            Result.Success(resp.body())
        } else {
            Result.Error(resp.message())
        }
    }

    override suspend fun getHourForecast(city: String): Result<Forecast> {
        val resp = api.getHourForecast(city)
        return if (resp.isSuccessful) {
            Result.Success(resp.body())
        } else {
            Result.Error(resp.message())
        }
    }

    override suspend fun getWeekForecast(
        lon: Double?,
        lat: Double?
    ): Result<WeekForecast> {
        val resp = api.getWeekForecast(lon, lat)
        return if (resp.isSuccessful) {
            Result.Success(resp.body())
        } else {
            Result.Error(resp.message())
        }
    }
}
