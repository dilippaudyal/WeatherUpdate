package com.code.challenge.wateher.weatherupdate.data.model


import com.squareup.moshi.Json

data class Sys(
    @Json(name = "country")
    val country: String?,
    @Json(name = "sunrise")
    val sunrise: Long?,
    @Json(name = "sunset")
    val sunset: Long?
)