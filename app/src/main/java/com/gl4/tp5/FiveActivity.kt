package com.gl4.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.tp5.databinding.ActivityFiveBinding
import com.gl4.tp5.databinding.ActivityMainBinding

class FiveActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFiveBinding
    private val model: MainViewModel by viewModels()
    private var ville = ""
    private var lat : Float = 0.0F
    private var lon : Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ville = intent.getStringExtra("ville").toString()
        print("-------------------")
        println(ville)
        lat = intent.getFloatExtra("latt",0.0F)
        print("-------------------")
        println(lat)
        lon = intent.getFloatExtra("lonn",0.0F)
        print("-------------------")
        println(lon)

        var adapter = recyclerViewAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.adapter = adapter

        // Create the observer which update the UI
        val forcastObserver = Observer<List<ForecastWeather>> {
            //adapter
            adapter.setDataSet(it)
        }

        model.Forecasts.observe(this,forcastObserver)
    }
}