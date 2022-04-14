package ru.geekbrains.a1919_2_myweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.a1919_2_myweather.repository.RepositoryImpl

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getWeatherRussia() = getWeather(true)
    fun getWeatherWorld() = getWeather(false)

    private fun getWeather(isRussian: Boolean) {
        Thread {
            liveData.postValue(AppState.Loading)
            val answer =
                if (!isRussian) repository.getWorldWeatherFromLocaleStorage() else repository.getRussianWeatherFromLocaleStorage()
            liveData.postValue(AppState.Success(answer))
        }.start()
    }
}