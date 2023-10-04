package com.udacity.asteroidradar

import java.text.SimpleDateFormat
import java.util.Date

object Constants {
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"

    const val NEO_PATH = "neo/rest/v1/feed"
    const val APOD_PATH = "planetary/apod"

    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        return formatter.format(date)
    }
}