package ru.geekbrains.a1919_2_myweather.repository

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWorldWeatherFromLocaleStorage(): List<Weather>
    fun getRussianWeatherFromLocaleStorage(): List<Weather>
}