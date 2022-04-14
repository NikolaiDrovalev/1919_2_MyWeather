package ru.geekbrains.a1919_2_myweather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.a1919_2_myweather.R
import ru.geekbrains.a1919_2_myweather.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
    }
}