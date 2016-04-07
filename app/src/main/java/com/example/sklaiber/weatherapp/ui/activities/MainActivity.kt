package com.example.sklaiber.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.sklaiber.weatherapp.R
import com.example.sklaiber.weatherapp.domain.commands.RequestForecastCommand
import com.example.sklaiber.weatherapp.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val forecastList: RecyclerView = find(R.id.forecast_list)
    forecastList.layoutManager = LinearLayoutManager(this)

    async() {
      val result = RequestForecastCommand("94043").execute()
      uiThread {
        forecastList.adapter = ForecastListAdapter(result)
      }
    }

  }
}