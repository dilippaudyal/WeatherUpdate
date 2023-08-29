package com.code.challenge.wateher.weatherupdate.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code.challenge.wateher.weatherupdate.R
import com.code.challenge.wateher.weatherupdate.data.model.WeatherResponse
import com.code.challenge.wateher.weatherupdate.databinding.WeatherUpdateListItemBinding
import com.code.challenge.wateher.weatherupdate.utils.convertDateTime
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri


class WeatherAdapter(
    private val weatherResponse: ArrayList<WeatherResponse>
) : RecyclerView.Adapter<WeatherAdapter.DataViewHolder>() {

    private val TAG = WeatherAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            WeatherUpdateListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return weatherResponse.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val list = weatherResponse[position]
        holder.apply {
            bind(list)
        }
    }

    class DataViewHolder(
        private var binding: WeatherUpdateListItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: WeatherResponse) {
            val temp = if (data.main?.temp.toString().contains("."))
                data.main?.temp.toString().split(".")[0]
            else
                data.main?.temp.toString()

            binding.tvActualTem.text = temp

            binding.tvAirQuality.text = data.weather?.get(0)?.main
            binding.tvAirQualityIndexValue.text = "N/A"

            binding.tvSunRise.text =
                "Sunrise ${data.sys?.sunrise?.let { convertDateTime(it).split(" ")[1] }}"
            binding.tvSunSet.text =
                "Sunset ${data.sys?.sunset?.let { convertDateTime(it).split(" ")[1] }}"

            if (data.main?.feelsLike == null)
                binding.tvLabelFeelLikeValue.text =
                    "${temp}${binding.tvLabelFeelLikeValue.context.getString(R.string.degree_celsius)}"
            else
                binding.tvLabelFeelLikeValue.text = "${data.main?.feelsLike}${
                    binding.tvLabelFeelLikeValue.context.getString(R.string.degree_celsius)
                }"

            binding.tvLabelHumidityValue.text = "${data.main?.humidity.toString()}%"
            binding.tvLabelChancesOfRainValue.text =
                "${data.main?.tempMin}${binding.tvLabelFeelLikeValue.context.getString(R.string.degree_celsius)}"
            binding.tvLabelPressureValue.text = "${data.main?.pressure.toString()}mbar"
            binding.tvLabelWindSpeedValue.text = "${data.wind?.speed.toString()}km/h"
            binding.tvLabelUvIndexValue.text =
                "${data.main?.tempMax}${binding.tvLabelFeelLikeValue.context.getString(R.string.degree_celsius)}"


            binding.tvMore.setOnClickListener {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://openweathermap.org/city/${data.id}")
                )
                startActivity(binding.tvMore.context, browserIntent, null)
            }
        }
    }
}

