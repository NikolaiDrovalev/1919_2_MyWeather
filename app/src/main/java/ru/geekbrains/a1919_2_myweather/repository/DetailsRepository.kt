package ru.geekbrains.a1919_2_myweather.repository

import ru.geekbrains.a1919_2_myweather.viewmodel.DetailsViewModel


interface DetailsRepository {
    fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback)
}