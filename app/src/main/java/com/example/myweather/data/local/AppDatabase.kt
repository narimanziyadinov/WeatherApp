package com.example.myweather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myweather.data.local.db.dao.CityDao
import com.example.myweather.data.local.db.entity.CityWeatherEntity

@Database(
    entities = [CityWeatherEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}