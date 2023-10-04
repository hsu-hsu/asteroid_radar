package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val database = AsteroidsDatabase.getInstance(application)
    private val repository = AsteroidsRepository(database)

    val asteroids = repository.asteroids

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    private val _navigateToDetailFragment = MutableLiveData<Asteroid?>()
    val navigateToDetailFragment
        get() = _navigateToDetailFragment

    init {
        refreshAsteroids()
        getPictureOfDay()
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getPictureOfDay() {
        viewModelScope.launch {
            try {
                _pictureOfDay.value = repository.getPictureOfDay()
                Log.i("pic", "here picture of the day"+ _pictureOfDay.value!!.url)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onAsteroidItemClick(data: Asteroid) {
        _navigateToDetailFragment.value = data
    }

    fun onDetailFragmentNavigate() {
        _navigateToDetailFragment.value = null
    }
}