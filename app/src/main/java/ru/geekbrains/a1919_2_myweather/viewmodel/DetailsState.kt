package ru.geekbrains.a1919_2_myweather.viewmodel

import ru.geekbrains.a1919_2_myweather.repository.Weather

sealed class DetailsState {
    object Loading : DetailsState()
    data class Success(val weather: Weather) : DetailsState()
    data class Error(val error: Throwable) : DetailsState()
}