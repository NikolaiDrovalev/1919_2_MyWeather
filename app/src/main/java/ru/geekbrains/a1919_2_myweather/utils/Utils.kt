package ru.geekbrains.a1919_2_myweather.utils

import ru.geekbrains.a1919_2_myweather.repository.Weather
import ru.geekbrains.a1919_2_myweather.repository.dto.FactDTO
import ru.geekbrains.a1919_2_myweather.repository.dto.WeatherDTO
import ru.geekbrains.a1919_2_myweather.repository.getDefaultCity


const val KEY_BUNDLE_WEATHER = "weather"
const val YANDEX_API_KEY = "X-Yandex-API-Key"
const val KEY_BUNDLE_LAT = "lat"
const val KEY_BUNDLE_LON = "lon"
const val KEY_BUNDLE_SERVICE_BROADCAST_WEATHER = "weather_service_broadcast"
const val KEY_WAVE_SERVICE_BROADCAST = "wave_service_broadcast"
const val YANDEX_DOMAIN_HARD_MODE = "http://212.86.114.27/"
const val YANDEX_ENDPOINT = "v2/informers?"


class Utils {
}


fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    val fact: FactDTO = weatherDTO.factDTO
    return (Weather(getDefaultCity(), fact.temperature, fact.feelsLike, fact.icon))
}