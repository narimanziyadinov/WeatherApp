package com.example.myweather.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class DailyDto(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    @SerializedName(value = "feels_like")
    val feelsLike: FeelsLikeDto,
    val humidity: Int,
    val moon_phase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: TempDto,
    val uvi: Double,
    val weather: List<WeatherDto>,
    @SerializedName(value = "wind_deg")
    val windDeg: Int,
    @SerializedName(value = "wind_gust")
    val windGust: Double,
    @SerializedName(value = "wind_speed")
    val windSpeed: Double
)