package com.code.challenge.wateher.weatherupdate.data.model


import com.squareup.moshi.Json

data class Coord(
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lon")
    val lon: Double?
)