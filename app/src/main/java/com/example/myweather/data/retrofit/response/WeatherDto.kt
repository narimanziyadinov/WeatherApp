package com.example.myweather.data.retrofit.response

data class WeatherDto(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)