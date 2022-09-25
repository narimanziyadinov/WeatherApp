package com.example.myweather.domain.model

data class WeatherList(
    val code: String,
    val count: Int,
    val list: List<CurrentWeather>,
    val message: String
)