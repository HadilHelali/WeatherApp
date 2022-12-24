package com.gl4.tp5.models

import android.util.Log
import retrofit2.Callback
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp5.api.forecastResponse.ForecastResponse
import com.gl4.tp5.objects.RetrofitHelper
import retrofit2.Call
import retrofit2.Response


class FiveViewModel : ViewModel() {
    val currentForecasts = MutableLiveData<ForecastResponse>()
    var Forecasts : LiveData<ForecastResponse> = currentForecasts

    init {
        getWeatherForecast("Tunis")
    }

    public fun getWeatherForecast(ville : String){
        RetrofitHelper.retrofitService.getWeatherForecast(ville).enqueue(
            object : Callback<ForecastResponse>{
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    println("on Success")
                    println("isSuccessful :"+response.isSuccessful)
                    if(response.isSuccessful) {
                        currentForecasts.value = response.body()
                        //Forecasts = currentForecasts
                        println(currentForecasts.value)
                        Log.d("ONSUCCESS" ,response.toString() )
                    }
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.d("ONFAILURE2" , t.message.toString())
                }

            }
        )
    }
}