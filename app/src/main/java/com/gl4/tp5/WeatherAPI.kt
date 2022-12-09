package com.gl4.tp5

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather?APPID=0c96d4ff43819ccc6e8704616d353110")
    fun getWeather(@Query("q") ville : String) : Call<WeatherResponse>

    @GET("forecast/daily?APPID=0c96d4ff43819ccc6e8704616d353110")
    fun getWeatherForecast(@Query("lat") lat : Float,@Query("lon") lon : Float,@Query("cnt") cnt : Int): Call<ForecastResponse>
}