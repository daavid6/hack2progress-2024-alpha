package com.example.firelight.network

import com.example.firelight.constants.Const.Companion.OPEN_WEATHER_MAP_API_KEY
import com.example.firelight.model.weather.DataResult
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("weather")
    suspend fun getWeatherByCoord(
        @Query("lat") lat: Double = 43.46472,
        @Query("lon") lon: Double = -3.80444,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = OPEN_WEATHER_MAP_API_KEY,
        @Query("exclude") exclude: String = "minutely,hourly,daily,alerts,hourly",
        @Query("lang") lang: String = "es"
    ): DataResult

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String = "metric",
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = OPEN_WEATHER_MAP_API_KEY,
    ): DataResult
}