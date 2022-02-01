package com.code.challenge.uxcam.weatherupdate.main.viewstate

import com.code.challenge.uxcam.weatherupdate.data.model.WeatherResponse

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Weather(val response: WeatherResponse) : MainState()
    data class Error(val error: String) : MainState()

}
