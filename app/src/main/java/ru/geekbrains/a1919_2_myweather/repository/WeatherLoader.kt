package ru.geekbrains.a1919_2_myweather.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import ru.geekbrains.a1919_2_myweather.BuildConfig
import ru.geekbrains.a1919_2_myweather.repository.dto.WeatherDTO
import ru.geekbrains.a1919_2_myweather.utils.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat: Double, lon: Double) {
        val urlText =
            "${YANDEX_DOMAIN_HARD_MODE}${YANDEX_ENDPOINT}$KEY_BUNDLE_LAT=$lat&$KEY_BUNDLE_LON=$lon"
        val uri = URL(urlText)

        Thread {
            val urlConnection: HttpURLConnection =
                (uri.openConnection() as HttpURLConnection).apply {
                    connectTimeout = 1000
                    readTimeout = 1000
                    addRequestProperty(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
                }
            try {
                val responseCode = urlConnection.responseCode
                val responseMessage = urlConnection.responseMessage
                val serverSide = 500..599
                val clientSide = 400..499
                val responseOk = 200..299
                when (responseCode) {
                    in serverSide -> {
                        Log.e("@@@", "$responseCode + $responseMessage")
                    }
                    in clientSide -> {
                        Log.e("@@@", "$responseCode + $responseMessage")
                    }
                    in responseOk -> {
                        Log.e("@@@", "$responseCode + $responseMessage")
                        val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                        val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                        Handler(Looper.getMainLooper()).post {
                            onServerResponseListener.onResponse(weatherDTO)
                        }
                    }
                }
            } finally {
                urlConnection.disconnect()
            }

        }.start()
    }
}