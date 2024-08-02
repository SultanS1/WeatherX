package com.test.weatherx.cities.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApi {

    @GET("/current.json")
    suspend fun getCityInfo(@Query("q") city: String)

}