package com.example.sklaiber.weatherapp.domain.mappers

import com.example.sklaiber.weatherapp.data.server.Forecast
import com.example.sklaiber.weatherapp.data.server.ForecastResult
import com.example.sklaiber.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import com.example.sklaiber.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {

  fun convertFromDataModel(zipCode: Long, forecast: ForecastResult) = with(forecast) {
    ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
  }

  private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
    return list.map { convertForecastItemToDomain(it) }
  }

  private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
    ModelForecast(dt * 1000, weather[0].description, temp.max.toInt(), temp.min.toInt(),
        generateIconUrl(weather[0].icon))
  }

  private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}