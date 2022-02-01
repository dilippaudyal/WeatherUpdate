package com.code.challenge.uxcam.weatherupdate.data.model


import com.squareup.moshi.Json

data class Clouds(
    @Json(name = "all")
    val all: Int?
)