package com.code.challenge.uxcam.weatherupdate.data.model


import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = "cod")
    val cod: String?,
    @Json(name = "message")
    val message: String?
)