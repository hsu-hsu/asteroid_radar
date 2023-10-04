package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidsDatabaseDao {

    @Query("SELECT * FROM asteroids_table ORDER by closeApproachDate")
    fun getAll(): LiveData<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidEntity>)

    @Query("SELECT * FROM asteroids_table WHERE closeApproachDate <=:date ORDER BY closeApproachDate ")
    fun getTodayAsteroid(date: String): LiveData<List<Asteroid>>
}