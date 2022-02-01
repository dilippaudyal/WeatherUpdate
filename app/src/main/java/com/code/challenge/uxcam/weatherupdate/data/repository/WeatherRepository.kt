package com.code.challenge.uxcam.weatherupdate.data.repository

import com.code.challenge.uxcam.weatherupdate.data.api.ApiHelper


class WeatherRepository(private val apiHelper: ApiHelper) {

    suspend fun getWeather(cityName: String?) = apiHelper.getWeather(cityName)

}