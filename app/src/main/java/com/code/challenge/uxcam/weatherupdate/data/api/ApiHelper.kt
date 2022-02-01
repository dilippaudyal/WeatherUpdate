package com.code.challenge.uxcam.weatherupdate.data.api

import com.code.challenge.uxcam.weatherupdate.data.model.WeatherResponse

interface ApiHelper {

    suspend fun getWeather(cityName : String?): WeatherResponse

}