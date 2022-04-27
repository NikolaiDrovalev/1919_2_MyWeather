package ru.geekbrains.a1919_2_myweather.repository

data class ForecastDTO(
    val date: String,
    val date_ts: Int,
    val moon_code: Int,
    val moon_text: String,
    val partDTOS: List<PartDTO>,
    val sunrise: String,
    val sunset: String,
    val week: Int
)