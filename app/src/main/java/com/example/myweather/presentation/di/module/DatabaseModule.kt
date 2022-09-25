package com.example.myweather.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.example.myweather.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(
        @ApplicationContext context: Context
    ): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .build()

    companion object {
        private const val DATABASE_NAME = "city_weather"
    }
}
