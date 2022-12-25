package com.gl4.tp5.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.tp5.models.FiveViewModel
import com.gl4.tp5.databinding.ActivityFiveBinding
import com.gl4.tp5.adapters.recyclerViewAdapter

class FiveActivity : AppCompatActivity() {

    private var ville = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding : ActivityFiveBinding

        val model: FiveViewModel by viewModels()


        super.onCreate(savedInstanceState)
        binding = ActivityFiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ville = intent.getStringExtra("ville").toString()
        model.getWeatherForecast(ville)

        model.Forecasts.observe( this){
            if(it!=null){
                binding.recyclerview.adapter = recyclerViewAdapter(model.Forecasts.value)
            }
        }

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(this@FiveActivity)
            adapter = recyclerViewAdapter(model.Forecasts.value)
            val dividerItemDecoration = DividerItemDecoration(
                binding.recyclerview.context,
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(dividerItemDecoration)
        }
    }
}