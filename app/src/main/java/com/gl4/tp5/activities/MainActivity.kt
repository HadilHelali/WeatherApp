package com.gl4.tp5.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gl4.tp5.models.MainViewModel
import com.gl4.tp5.api.weatherResponse.WeatherResponse
import com.gl4.tp5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val Ville_list = listOf("Tunis", "Mahdia", "Sousse")
    private var selectedVille : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
         lateinit var binding : ActivityMainBinding
         val model: MainViewModel by viewModels()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line , Ville_list
        )
        binding.spinner.adapter = adapter

        // gérer les clics sur un élement d'un spinner:
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedVille = adapterView?.getItemAtPosition(position).toString()
                model.getweather(selectedVille)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // nothing
            }
        }
        // Create the observer which update the UI

        val weatherObserver = Observer<WeatherResponse> {
            binding.country.text = it.name
            println("---"+it.name)
            //log.d("TAG", "---"+it.name)
            binding.weatherInfo.text = it.weather.get(0).description
            binding.Temp.text = it.main.temp.toString()+"°C"
            binding.humidity.text = "Humidity : "+it.main.humidity.toString()
            binding.pressure.text = "Pressure : "+it.main.pressure.toString()
            binding.weatherImage.loadUrl("https://openweathermap.org/img/wn/" + it.weather.get(0).icon + "@4x.png")
        }
       model.weather.observe(this,weatherObserver)
    }

    fun GoToAc5(view: View) {
        val intent = Intent(this, FiveActivity::class.java )
        intent.putExtra("ville",selectedVille)
        startActivity(intent)
    }
}