package com.example.sklaiber.weatherapp.data.db

import com.example.sklaiber.weatherapp.domain.model.Forecast
import com.example.sklaiber.weatherapp.domain.model.ForecastList

class DbDataMapper {

  fun convertFromDomain(forecast: ForecastList) = with(forecast) {
    val daily = dailyForecast.map { convertDayFromDomain(id, it) }
    CityForecast(id, city, country, daily)
  }

  private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
    DayForecast(date, description, high, low, iconUrl, cityId)
  }

  fun convertToDomain(forecast: CityForecast) = with(forecast) {
    val daily = dailyForecast.map { convertDayToDomain(it) }
    ForecastList(_id, city, country, daily)
  }

  private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
    Forecast(date, description, high, low, iconUrl)
  }
}