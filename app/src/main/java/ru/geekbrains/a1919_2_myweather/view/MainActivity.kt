package ru.geekbrains.a1919_2_myweather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.a1919_2_myweather.R
import ru.geekbrains.a1919_2_myweather.view.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}