package ru.geekbrains.a1919_2_myweather.repository

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
)

fun getDefaultCity() = City("Москва", 55.75, 37.61)

data class City(val name: String, val lat: Double, val lon: Double)