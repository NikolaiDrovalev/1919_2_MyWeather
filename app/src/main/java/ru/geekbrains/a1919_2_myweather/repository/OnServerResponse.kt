package ru.geekbrains.a1919_2_myweather.repository

import ru.geekbrains.a1919_2_myweather.repository.dto.WeatherDTO

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}