package com.gl4.tp5

data class ForecastResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ForecastWeather>,
    val message: Double
)