package com.example.myweather.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class HourWeatherDto(
    val clouds: CloudsDto,
    val dt: Int,
    @SerializedName(value = "dt_txt")
    val dtTxt: String,
    val main: MainDto,
    val pop: Double,
    val rain: RainDto,
    val sys: SysDto,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
)