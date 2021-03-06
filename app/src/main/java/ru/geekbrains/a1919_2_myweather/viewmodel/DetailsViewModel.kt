package ru.geekbrains.a1919_2_myweather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.a1919_2_myweather.repository.City
import ru.geekbrains.a1919_2_myweather.repository.DetailsRepository
import ru.geekbrains.a1919_2_myweather.repository.DetailsRepositoryRetrofit2Impl
import ru.geekbrains.a1919_2_myweather.repository.Weather

class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private val repository: DetailsRepository = DetailsRepositoryRetrofit2Impl()
) : ViewModel() {

    fun getLiveData() = liveData
    fun getWeather(city: City) {
        liveData.postValue(DetailsState.Loading)
        repository.getWeatherDetails(city, object : Callback {
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
            }

            override fun onFail() {
                liveData.postValue(DetailsState.Error(Throwable()))
            }
        })
    }

    interface Callback {
        fun onResponse(weather: Weather)
        fun onFail()
    }
}