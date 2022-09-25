package com.example.myweather.presentation.di.module

import com.example.myweather.data.local.AppDatabase
import com.example.myweather.data.repository.LocalRepositoryImpl
import com.example.myweather.data.local.db.dao.CityDao
import com.example.myweather.data.repository.WeatherRepositoryImpl
import com.example.myweather.data.retrofit.WeatherApi
import com.example.myweather.domain.repository.LocalRepository
import com.example.myweather.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideWeatherRepository(
        api: WeatherApi,
        dao: CityDao
    ): WeatherRepository = WeatherRepositoryImpl(api, dao)

    @Provides
    fun provideChooseCityRepository(
        dao: CityDao
    ): LocalRepository = LocalRepositoryImpl(dao)

    @Provides
    fun provideCityDao(
        db: AppDatabase
    ): CityDao = db.cityDao()
}
