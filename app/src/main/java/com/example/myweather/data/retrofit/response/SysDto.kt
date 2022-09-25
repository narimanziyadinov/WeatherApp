package com.example.myweather.data.retrofit.response

data class SysDto(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)