package com.code.challenge.wateher.weatherupdate.data.api

import com.code.challenge.wateher.weatherupdate.data.model.WeatherResponse

interface ApiHelper {

    suspend fun getWeather(cityName : String?): WeatherResponse

}