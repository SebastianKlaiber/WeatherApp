package com.example.sklaiber.weatherapp.ui

import android.app.Application
import com.example.sklaiber.weatherapp.extentsions.DelegatesExt

class App : Application(){

  companion object {
    var instance: App by DelegatesExt.notNullSingleValue()
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
  }
}