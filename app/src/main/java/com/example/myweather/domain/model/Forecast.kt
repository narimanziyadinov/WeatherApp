package com.example.myweather.domain.model

import com.example.myweather.data.retrofit.response.CityDto
import com.example.myweather.data.retrofit.response.HourWeatherDto

data class Forecast(
    val city: CityDto,
    val cnt: Int,
    val cod: String,
    val list: List<HourWeatherDto>,
    val message: Int
)