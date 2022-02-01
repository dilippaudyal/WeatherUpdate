package com.code.challenge.uxcam.weatherupdate.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.code.challenge.uxcam.weatherupdate.data.api.ApiHelper
import com.code.challenge.uxcam.weatherupdate.data.repository.WeatherRepository
import com.code.challenge.uxcam.weatherupdate.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(WeatherRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}