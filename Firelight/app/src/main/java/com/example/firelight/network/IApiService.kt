package com.example.firelight.network

import com.example.firelight.constants.Const.Companion.OPEN_WEATHER_MAP_API_KEY
import com.example.firelight.model.weather.ValidData
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = OPEN_WEATHER_MAP_API_KEY,
    ): ValidData // WeatherResult

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String = "metric",
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = OPEN_WEATHER_MAP_API_KEY,
    ): ValidData //WeatherResult
}