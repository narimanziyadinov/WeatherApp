package com.example.myweather.domain.model

import com.example.myweather.data.retrofit.response.*

data class CurrentWeather(
    val base: String,
    val clouds: CloudsDto,
    val cod: Int,
    val coord: CoordDto,
    val dt: Int,
    val id: Int,
    val main: MainDto,
    val name: String,
    val rain: RainDto,
    val sys: SysDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
)