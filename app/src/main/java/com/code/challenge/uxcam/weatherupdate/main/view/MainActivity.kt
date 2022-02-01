package com.code.challenge.uxcam.weatherupdate.main.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.wtf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.code.challenge.uxcam.weatherupdate.R
import com.code.challenge.uxcam.weatherupdate.data.api.ApiHelperImpl
import com.code.challenge.uxcam.weatherupdate.data.api.RetrofitBuilder
import com.code.challenge.uxcam.weatherupdate.data.model.WeatherResponse
import com.code.challenge.uxcam.weatherupdate.databinding.ActivityMainBinding
import com.code.challenge.uxcam.weatherupdate.main.adapter.WeatherAdapter
import com.code.challenge.uxcam.weatherupdate.main.intent.MainIntent
import com.code.challenge.uxcam.weatherupdate.main.viewmodel.MainViewModel
import com.code.challenge.uxcam.weatherupdate.main.viewstate.MainState
import com.code.challenge.uxcam.weatherupdate.utils.ViewModelFactory
import com.code.challenge.uxcam.weatherupdate.utils.genericMessageDialog
import com.code.challenge.uxcam.weatherupdate.utils.locationAddDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val DEFAULT_CITY = "Kathmandu"

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private var weatherList = ArrayList<WeatherResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        observeViewModel()
        setUpIntent(DEFAULT_CITY)
        setUpClicks()
    }

    private fun setUpClicks() {

        binding.imgAdd.setOnClickListener {
            addLocation()
        }
    }

    private fun addLocation() {

        locationAddDialog(getCityName = {
            it?.let { it1 -> setUpIntent(it1) }
        })
    }

    private fun setUpIntent(cityName: String) {

        if (cityName.isNotEmpty())
            mainViewModel.cityName.value = cityName

        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchWeather)
        }
    }

    private fun setupViewModel() {

        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService()
                )
            )
        )[MainViewModel::class.java]
    }

    private fun observeViewModel() {

        lifecycleScope.launch {

            mainViewModel.state.collect() {
                when (it) {
                    is MainState.Idle -> {}
                    is MainState.Loading -> {}
                    is MainState.Weather -> {
                        weatherList.add(it.response)
                        renderList()
                    }
                    is MainState.Error -> {
                        genericMessageDialog("OK", it.error, handlerUserAction = {
                            // Handel User action accordingly
                        })
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList() {
        val adapter = WeatherAdapter(weatherList)
        binding.vpWeatherDetailsList.apply {
            this.adapter = adapter
            this.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.tvCityName.text = weatherList[position].name
                }
            })
        }
        adapter.notifyDataSetChanged()
        if (weatherList.size > 1)
            binding.dotsIndicator.setViewPager2(binding.vpWeatherDetailsList)

    }
}