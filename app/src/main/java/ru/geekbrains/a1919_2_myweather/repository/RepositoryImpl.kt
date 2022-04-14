package ru.geekbrains.a1919_2_myweather.repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        Thread.sleep(2000L)
        return Weather()
    }

    override fun getWorldWeatherFromLocaleStorage(): List<Weather> {
        return getWorldCities()
    }

    override fun getRussianWeatherFromLocaleStorage(): List<Weather> {
        return getRussianCities()
    }

}