package com.test.weatherx.common.data.remote

import com.test.weatherx.common.data.dto.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getCityInfo(@Query("q") city: String): Response<WeatherResponse>

}