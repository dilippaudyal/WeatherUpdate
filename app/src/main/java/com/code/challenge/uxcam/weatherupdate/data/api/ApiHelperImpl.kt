package com.code.challenge.uxcam.weatherupdate.data.api

import com.code.challenge.uxcam.weatherupdate.data.model.WeatherResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeather(cityName: String?): WeatherResponse {
        return apiService.getWeatherDataByCityName(cityName)
    }
}