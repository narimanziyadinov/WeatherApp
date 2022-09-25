package com.example.myweather.data.retrofit.response

data class CityDto(
    val coord: CoordDto,
    val country: String,
    val id: Int,
    val name: String,
    val population: Double,
    val sunrise: Double,
    val sunset: Double,
    val timezone: Double
)