package ru.geekbrains.a1919_2_myweather.view.details

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import ru.geekbrains.a1919_2_myweather.BuildConfig
import ru.geekbrains.a1919_2_myweather.repository.dto.WeatherDTO
import ru.geekbrains.a1919_2_myweather.utils.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class DetailsService(val name: String = "") : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@", "work DetailsService")
        intent?.let {
            val lat = it.getDoubleExtra(KEY_BUNDLE_LAT, 0.0)
            val lon = it.getDoubleExtra(KEY_BUNDLE_LON, 0.0)
            Log.d("@@@", "work DetailsService $lat $lon")

            val urlText = "$YANDEX_DOMAIN_HARD_MODE${YANDEX_PATH}lat=$lat&lon=$lon"
            val uri = URL(urlText)

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
                        val message = Intent(KEY_WAVE_SERVICE_BROADCAST)
                        message.putExtra(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER, weatherDTO)
                        LocalBroadcastManager.getInstance(this).sendBroadcast(message)
                    }
                    else -> {}
                }
            } finally {
                urlConnection.disconnect()
            }
        }
    }
}