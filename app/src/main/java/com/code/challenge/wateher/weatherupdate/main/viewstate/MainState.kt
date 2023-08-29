package com.code.challenge.wateher.weatherupdate.main.viewstate

import com.code.challenge.wateher.weatherupdate.data.model.WeatherResponse

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Weather(val response: WeatherResponse) : MainState()
    data class Error(val error: String) : MainState()

}
