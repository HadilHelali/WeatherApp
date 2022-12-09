package com.gl4.tp5

import android.util.Log
import retrofit2.Callback
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query


class MainViewModel : ViewModel() {
    val currentWeather = MutableLiveData<WeatherResponse>()
    val currentForecasts = MutableLiveData<List<ForecastWeather>>()
    val weather : LiveData<WeatherResponse> = currentWeather
    val Forecasts : LiveData<List<ForecastWeather>> = currentForecasts
    init {
        getweather("Tunis")
        getWeatherForecast(34F,9F,16)
    }
    public fun getweather(ville : String){
        RetrofitHelper.retrofitService.getWeather(ville).enqueue(object :
            Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if(response.isSuccessful) {
                    currentWeather.value = response.body()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("ONFAILURE" , t.message.toString())
            }


        })
    }

    public fun getWeatherForecast(lat : Float, lon : Float , cnt : Int){
        RetrofitHelper.retrofitService.getWeatherForecast(lat,lon,cnt).enqueue(
            object : Callback<ForecastResponse>{
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if(response.isSuccessful) {
                        println(response)
                        currentForecasts.value = response.body()?.list
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.d("ONFAILURE2" , t.message.toString())
                }

            }
        )
    }
}