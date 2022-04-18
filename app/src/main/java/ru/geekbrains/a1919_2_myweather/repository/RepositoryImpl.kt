package ru.geekbrains.a1919_2_myweather.repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()

    override fun getWorldWeatherFromLocaleStorage() = getWorldCities()

    override fun getRussianWeatherFromLocaleStorage() = getRussianCities()

}