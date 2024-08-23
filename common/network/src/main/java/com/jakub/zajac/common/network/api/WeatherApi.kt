package com.jakub.zajac.common.network.api

import com.jakub.zajac.common.network.model.WeatherDailyResponse
import com.jakub.zajac.common.network.model.WeatherHourlyDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecasts/v1/hourly/12hour/{locationKey}/")
    suspend fun getHourlyWeather(
        @Path("locationKey") locationKey: String,
        @Query("details") details: Boolean,
        @Query("metric") metric: Boolean
    ): Response<List<WeatherHourlyDto>>

    @GET("forecasts/v1/daily/5day/{locationKey}/")
    suspend fun getDailyWeather(
        @Path("locationKey") locationKey: String,
        @Query("details") details: Boolean,
        @Query("metric") metric: Boolean
    ): Response<WeatherDailyResponse>
}