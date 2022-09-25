package com.example.myweather.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_weather")
data class CityWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    val cityId: Int,
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val timezone: Int
)
