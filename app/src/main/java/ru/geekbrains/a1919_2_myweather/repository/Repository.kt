package ru.geekbrains.a1919_2_myweather.repository

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocaleStorage(): Weather
}