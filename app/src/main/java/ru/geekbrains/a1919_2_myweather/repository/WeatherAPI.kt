package ru.geekbrains.a1919_2_myweather.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.geekbrains.a1919_2_myweather.repository.dto.WeatherDTO
import ru.geekbrains.a1919_2_myweather.utils.KEY_BUNDLE_LAT
import ru.geekbrains.a1919_2_myweather.utils.KEY_BUNDLE_LON
import ru.geekbrains.a1919_2_myweather.utils.YANDEX_API_KEY
import ru.geekbrains.a1919_2_myweather.utils.YANDEX_ENDPOINT

interface WeatherAPI {
    @GET(YANDEX_ENDPOINT)
    fun getWeather(
        @Header(YANDEX_API_KEY) apikey: String,
        @Query(KEY_BUNDLE_LAT) lat: Double,
        @Query(KEY_BUNDLE_LON) lon: Double
    ): Call<WeatherDTO>
}