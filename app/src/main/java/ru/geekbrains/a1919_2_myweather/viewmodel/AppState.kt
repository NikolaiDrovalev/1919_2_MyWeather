package ru.geekbrains.a1919_2_myweather.viewmodel

import ru.geekbrains.a1919_2_myweather.repository.Weather

sealed class AppState {
    object Loading : AppState()
    data class Success(val weatherListData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
}