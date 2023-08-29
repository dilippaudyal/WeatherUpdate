package com.code.challenge.wateher.weatherupdate.data.api

import com.code.challenge.wateher.weatherupdate.data.model.WeatherResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeather(cityName: String?): WeatherResponse {
        return apiService.getWeatherDataByCityName(cityName)
    }
}