package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants.APOD_PATH
import com.udacity.asteroidradar.Constants.NEO_PATH
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidApiService {
    @GET(NEO_PATH)
    suspend fun getAsteroids(@Query("start_date") startDate: String,
                             @Query("end_date") endDate: String,
                             @Query("api_key") apiKey: String
    ): String

    @GET(APOD_PATH)
    suspend fun getApod(@Query("api_key") apiKey: String): PictureOfDay
}