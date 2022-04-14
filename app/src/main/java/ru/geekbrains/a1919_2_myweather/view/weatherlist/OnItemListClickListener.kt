package ru.geekbrains.a1919_2_myweather.view.weatherlist

import ru.geekbrains.a1919_2_myweather.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}