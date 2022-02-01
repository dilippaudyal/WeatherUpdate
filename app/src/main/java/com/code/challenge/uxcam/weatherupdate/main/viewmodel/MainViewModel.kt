package com.code.challenge.uxcam.weatherupdate.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.code.challenge.uxcam.weatherupdate.data.repository.WeatherRepository
import com.code.challenge.uxcam.weatherupdate.main.intent.MainIntent
import com.code.challenge.uxcam.weatherupdate.main.viewstate.MainState
import com.code.challenge.uxcam.weatherupdate.utils.convertErrorBody
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@ExperimentalCoroutinesApi
class MainViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    var cityName = MutableLiveData<String>()

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MainState>(MainState.Idle)

    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchWeather -> fetchWeather()
                }
            }
        }
    }

    private fun fetchWeather() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Weather(repository.getWeather(cityName.value))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val errorResponse = convertErrorBody(throwable)
                        MainState.Error(errorResponse?.message.toString())
                    }
                    else -> {
                        MainState.Error(throwable.message.toString())
                    }
                }
            }
        }
    }
}