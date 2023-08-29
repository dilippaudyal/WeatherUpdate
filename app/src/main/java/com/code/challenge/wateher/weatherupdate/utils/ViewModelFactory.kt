package com.code.challenge.wateher.weatherupdate.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.code.challenge.wateher.weatherupdate.data.api.ApiHelper
import com.code.challenge.wateher.weatherupdate.data.repository.WeatherRepository
import com.code.challenge.wateher.weatherupdate.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(WeatherRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}