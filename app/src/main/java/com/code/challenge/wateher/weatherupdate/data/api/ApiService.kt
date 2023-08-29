package com.code.challenge.wateher.weatherupdate.data.api

import com.code.challenge.wateher.weatherupdate.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {

        private const val APP_ID = "b647b75793358e3c8630620c09c249ae"
        private const val DEFAULT_TEMPERATURE_UNITS = "metric"

        private const val API_VERSION = "2.5"
        private const val WEATHER_ROOT = "weather"

        const val WEATHER_AIP = "${API_VERSION}/${WEATHER_ROOT}"

    }


    @GET(WEATHER_AIP)
    suspend fun getWeatherDataByCityName(
        @Query("q") cityName: String?,
        @Query("units") temperatureUnits: String? = DEFAULT_TEMPERATURE_UNITS,
        @Query("appid") appId: String? = APP_ID
    ): WeatherResponse

}