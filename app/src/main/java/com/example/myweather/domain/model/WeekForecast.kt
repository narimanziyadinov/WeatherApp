package com.example.myweather.domain.model

import com.example.myweather.data.retrofit.response.CurrentDto
import com.example.myweather.data.retrofit.response.DailyDto

data class WeekForecast(
    val current: CurrentDto,
    val daily: List<DailyDto>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)