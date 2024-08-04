package com.test.weatherx.selected.forecast.data.remote

import com.test.weatherx.selected.forecast.data.dto.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("days") days: Int = 5,
        @Query("tp") interval: Int = 24
    ): Response<WeatherForecastResponse>

}