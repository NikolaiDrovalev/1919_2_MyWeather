package ru.geekbrains.a1919_2_myweather.repository

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        Thread.sleep(2000L)
        return Weather()
    }

    override fun getWeatherFromLocaleStorage(): Weather {
        return Weather()
    }

}