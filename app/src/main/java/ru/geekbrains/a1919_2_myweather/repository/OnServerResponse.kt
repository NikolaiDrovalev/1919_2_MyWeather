package ru.geekbrains.a1919_2_myweather.repository

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}