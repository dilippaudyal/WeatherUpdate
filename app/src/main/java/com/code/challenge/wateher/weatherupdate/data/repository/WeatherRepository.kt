package com.code.challenge.wateher.weatherupdate.data.repository

import com.code.challenge.wateher.weatherupdate.data.api.ApiHelper


class WeatherRepository(private val apiHelper: ApiHelper) {

    suspend fun getWeather(cityName: String?) = apiHelper.getWeather(cityName)

}