package com.example.myweather.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class CurrentDto(
    val clouds: Int,
    @SerializedName(value = "dew_point")
    val dewPoint: Double,
    val dt: Double,
    @SerializedName(value = "feels_like")
    val feelsLike: Double,
    val humidity: Double,
    val pressure: Double,
    val sunrise: Double,
    val sunset: Double,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherDto>,
    @SerializedName(value = "wind_deg")
    val windDeg: Int,
    @SerializedName(value = "wind_gust")
    val windGust: Double,
    @SerializedName(value = "wind_speed")
    val windSpeed: Double
)