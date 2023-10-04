package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants.API_KEY
import com.udacity.asteroidradar.Constants.BASE_URL
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object AsteroidApi {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService : AsteroidApiService by lazy { retrofit.create(AsteroidApiService::class.java) }

    suspend fun getAsteroids() : List<Asteroid> {
        val responseStr = retrofitService.getAsteroids("","", API_KEY)
        val responseJsonObject = JSONObject(responseStr)

        return parseAsteroidsJsonResult(responseJsonObject)
    }

    suspend fun getPictureOfDay() = retrofitService.getApod(API_KEY)
}