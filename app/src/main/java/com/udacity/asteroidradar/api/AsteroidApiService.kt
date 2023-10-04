package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.APOD_PATH
import com.udacity.asteroidradar.Constants.NEO_PATH
import com.udacity.asteroidradar.PictureOfDay
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidApiService {
    @GET(NEO_PATH)
    suspend fun getAsteroids(@Query("start_date") startDate: String,
                             @Query("end_date") endDate: String,
                             @Query("api_key") apiKey: String): String

    @GET(APOD_PATH)
    suspend fun getApod(@Query("api_key") apiKey: String): PictureOfDay
}