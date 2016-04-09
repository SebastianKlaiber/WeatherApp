package com.example.sklaiber.weatherapp.domain.commands

import com.example.sklaiber.weatherapp.data.server.ForecastRequest
import com.example.sklaiber.weatherapp.domain.mappers.ForecastDataMapper
import com.example.sklaiber.weatherapp.domain.model.ForecastList

class RequestForecastCommand(val zipCode: Long) : Command<ForecastList> {
  override fun execute(): ForecastList {
    val forecastRequest = ForecastRequest(zipCode)
    return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
  }
}