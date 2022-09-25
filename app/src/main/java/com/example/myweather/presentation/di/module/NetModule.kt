package com.example.myweather.presentation.di.module

import com.example.myweather.data.retrofit.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetModule {


    companion object {
        private const val WEATHER_ROOT = "https://api.openweathermap.org/data/2.5/"
        private const val API_KEY = "fb47fa398ad290f6e16e655512d6e8d5"

        private const val READ_TIME_OUT = 60L
        private const val CONNECT_TIME_OUT = 30L

        private const val QUERY_PARAM = "appid"
    }

    @Provides
    fun provideKeyInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        original.url.newBuilder()
            .addQueryParameter(QUERY_PARAM, API_KEY)
            .build()
            .let {
                chain.proceed(
                    original.newBuilder().url(it).build()
                )
            }
    }


    @Provides
    fun provideOkHttp(
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .cache(null)
            .addInterceptor(interceptor)
            .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRetrofit(
        okHttp: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttp)
            .baseUrl(WEATHER_ROOT)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): WeatherApi =
        retrofit.create(WeatherApi::class.java)
}
