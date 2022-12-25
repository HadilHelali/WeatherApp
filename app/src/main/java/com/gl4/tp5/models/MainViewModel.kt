package com.gl4.tp5.models

import android.util.Log
import retrofit2.Callback
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp5.objects.RetrofitHelper
import com.gl4.tp5.api.weatherResponse.WeatherResponse
import retrofit2.Call
import retrofit2.Response


class MainViewModel : ViewModel() {
    val currentWeather = MutableLiveData<WeatherResponse>()
    val weather : LiveData<WeatherResponse> = currentWeather

    init {
        getweather("Tunis")
    }
    fun getweather(ville : String){
        RetrofitHelper.retrofitService.getWeather(ville).enqueue(object :
            Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                println("before success")
                if(response.isSuccessful) {
                    println("on success")
                    currentWeather.value = response.body()
                    println(currentWeather.value)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("ONFAILURE" , t.message.toString())
            }


        })
    }
}