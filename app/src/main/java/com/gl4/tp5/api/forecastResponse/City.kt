package com.gl4.tp5.api.forecastResponse

import com.gl4.tp5.api.weatherResponse.Coord

data class City(
    val coord: Coord,
    val country: String,
    val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)