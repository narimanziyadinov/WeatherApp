package com.example.myweather.data.retrofit.response

import com.google.gson.annotations.SerializedName

data class MainDto(
    @SerializedName(value = "feels_like")
    val feelsLike: Double,
    @SerializedName(value = "grnd_level")
    val groundLevel: Double,
    val humidity: Double,
    val pressure: Double,
    @SerializedName(value = "sea_level")
    val seaLevel: Double,
    val temp: Double,
    @SerializedName(value = "temp_max")
    val tempMax: Double,
    @SerializedName(value = "temp_min")
    val tempMin: Double
)