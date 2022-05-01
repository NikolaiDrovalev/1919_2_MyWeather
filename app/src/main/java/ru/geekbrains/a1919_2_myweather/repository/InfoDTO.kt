package ru.geekbrains.a1919_2_myweather.repository

import com.google.gson.annotations.SerializedName

data class InfoDTO(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("url")
    val url: String
)