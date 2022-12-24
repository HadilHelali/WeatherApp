package com.gl4.tp5.api

import com.gl4.tp5.api.forecastResponse.ForecastResponse
import com.gl4.tp5.api.weatherResponse.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("forecast/daily?APPID=17db59488cadcad345211c36304a9266")
    fun getWeatherForecast(@Query("q") ville : String): Call<ForecastResponse>

    @GET("weather?APPID=0c96d4ff43819ccc6e8704616d353110")
    fun getWeather(@Query("q") ville : String) : Call<WeatherResponse>
}