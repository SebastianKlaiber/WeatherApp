package com.example.sklaiber.weatherapp.domain.commands

interface Command<T> {
  fun execute(): T
}