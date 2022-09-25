package com.example.myweather.data.retrofit

import com.example.myweather.domain.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?units=metric")
    suspend fun getWeather(
        @Query("q") city: String
    ): Response<CurrentWeather>

    @GET("find?units=metric&lang=ru")
    suspend fun getWeatherWithCoordinates(
        @Query("lon") longitude: Double?,
        @Query("lat") latitude: Double?
    ): Response<CurrentWeather>

    @GET("forecast?units=metric&lang=ru&cnt=8")
    suspend fun getHourForecast(
        @Query("q") city: String?
    ): Response<Forecast>

    @GET("onecall?units=metric&exclude=minutely,hourly")
    suspend fun getWeekForecast(
        @Query("lon") longitude: Double?,
        @Query("lat") latitude: Double?
    ): Response<WeekForecast>

}
