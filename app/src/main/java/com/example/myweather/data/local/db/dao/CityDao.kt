package com.example.myweather.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweather.data.local.db.entity.CityWeatherEntity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(name: CityWeatherEntity)

    @Query("SELECT * FROM city_weather ORDER BY city_id DESC LIMIT 1")
    suspend fun getLastCity(): CityWeatherEntity?
}
