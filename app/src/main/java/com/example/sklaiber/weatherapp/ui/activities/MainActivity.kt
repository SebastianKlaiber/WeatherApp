package com.example.sklaiber.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.sklaiber.weatherapp.R
import com.example.sklaiber.weatherapp.domain.commands.RequestForecastCommand
import com.example.sklaiber.weatherapp.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.async
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    forecastList.layoutManager = LinearLayoutManager(this)

    async() {
      val result = RequestForecastCommand(94043).execute()
      uiThread {
        val adapter = ForecastListAdapter(result, { toast(it.description) })
        forecastList.adapter = adapter
      }
    }
  }
}
