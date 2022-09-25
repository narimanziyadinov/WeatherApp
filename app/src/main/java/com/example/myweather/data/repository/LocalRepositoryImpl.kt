package com.example.myweather.data.repository

import com.example.myweather.data.local.db.dao.CityDao
import com.example.myweather.data.local.db.entity.CityWeatherEntity
import com.example.myweather.domain.model.CurrentWeather
import com.example.myweather.domain.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: CityDao
) : LocalRepository {

    override suspend fun addCityWeather(weather: CurrentWeather) =
        dao.add(
            CityWeatherEntity(
                cityId = 0,
                name = weather.name,
                country = weather.sys.country,
                lat = weather.coord.lat,
                lon = weather.coord.lon,
                timezone = weather.timezone
            )
        )

    override suspend fun getLastCity(): String? {
        return dao.getLastCity()?.name
    }
}
