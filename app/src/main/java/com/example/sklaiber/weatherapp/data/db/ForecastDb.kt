package com.example.sklaiber.weatherapp.data.db

import com.example.sklaiber.weatherapp.domain.model.ForecastList
import com.example.sklaiber.weatherapp.extentsions.clear
import com.example.sklaiber.weatherapp.extentsions.parseList
import com.example.sklaiber.weatherapp.extentsions.parseOpt
import com.example.sklaiber.weatherapp.extentsions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
    val dataMapper: DbDataMapper = DbDataMapper()) {

  fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

    val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
    val dailyForecast = select(DayForecastTable.NAME)
        .whereSimple(dailyRequest, zipCode.toString(), date.toString())
        .parseList { DayForecast(HashMap(it)) }

    val city = select(CityForecastTable.NAME)
        .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
        .parseOpt { CityForecast(HashMap(it), dailyForecast) }

    if (city != null) dataMapper.convertToDomain(city) else null
  }

  fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

    clear(CityForecastTable.NAME)
    clear(DayForecastTable.NAME)

    with(dataMapper.convertFromDomain(forecast)) {
      insert(CityForecastTable.NAME, *map.toVarargArray())
      dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
    }
  }
}