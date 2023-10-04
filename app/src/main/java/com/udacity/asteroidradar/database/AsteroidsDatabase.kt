package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1,entities = [AsteroidEntity::class])
abstract class AsteroidsDatabase : RoomDatabase() {

    abstract val dao: AsteroidsDatabaseDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: AsteroidsDatabase

        fun getInstance(context: Context): AsteroidsDatabase {
            synchronized(this) {
                if(!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidsDatabase::class.java,
                        "asteroids_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}