package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.asAsteroidEntities
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asAsteroids
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidsRepository(private val database: AsteroidsDatabase) {

    val asteroids: LiveData<List<Asteroid>> =
        database.dao.getAll().map {
            it.asAsteroids()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroids = AsteroidApi.getAsteroids()
            database.dao.insertAll(asteroids.asAsteroidEntities())
        }
    }

    suspend fun getPictureOfDay(): PictureOfDay {
        lateinit var pictureOfDay: PictureOfDay
        withContext(Dispatchers.IO) {
            pictureOfDay = AsteroidApi.getPictureOfDay()
        }
        return pictureOfDay
    }
}