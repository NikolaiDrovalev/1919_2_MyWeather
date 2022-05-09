package ru.geekbrains.a1919_2_myweather.repository

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.geekbrains.a1919_2_myweather.BuildConfig
import ru.geekbrains.a1919_2_myweather.repository.dto.WeatherDTO
import ru.geekbrains.a1919_2_myweather.utils.*
import ru.geekbrains.a1919_2_myweather.viewmodel.DetailsViewModel

class DetailsRepositoryOkHttpImpl : DetailsRepository {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val client = OkHttpClient()
        val builder = Request.Builder()
        builder.url("$YANDEX_DOMAIN_HARD_MODE${YANDEX_ENDPOINT}$KEY_BUNDLE_LAT=${city.lat}&$KEY_BUNDLE_LON=${city.lon}")
        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)

        val request = builder.build()
        val call = client.newCall(request)
        Thread {
            val response = call.execute()
            if (response.isSuccessful) {
                val serverResponse = response.body()!!.string()
                val weatherDTO: WeatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                val weather = convertDtoToModel(weatherDTO)
                weather.city = city
                callback.onResponse(weather)
            }

        }.start()
    }
}