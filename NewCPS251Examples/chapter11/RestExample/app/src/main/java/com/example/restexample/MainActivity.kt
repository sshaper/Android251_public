package com.example.restexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherApi = createWeatherApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFetchWeather.setOnClickListener {
            val zipCode = binding.etZipCode.text.toString()
            if (zipCode.isNotEmpty()) {
                fetchWeather(zipCode)
            }
        }
    }

    private fun fetchWeather(zipCode: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = weatherApi.getCurrentWeather("3a32e73914444f0f818180726241305", zipCode)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        //binding.tvWeatherInfo.text = "Temperature: ${response.body()!!.current.temp_f} °F"
                        binding.tvTemp.text = "${response.body()!!.current.temp_f} °F"
                        binding.tvWeatherInfo.text = "Wind MPH ${response.body()!!.current.wind_mph}\nWind Direction ${response.body()!!.current.wind_dir}\nWind Gusts MPH ${response.body()!!.current.gust_mph}\nHumidity ${response.body()!!.current.humidity}%\nPrecipitation ${response.body()!!.current.precip_in}"


                    } else {
                        binding.tvWeatherInfo.text = "Failed to retrieve data."
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.tvWeatherInfo.text = "Error: ${e.message}"
                }
            }
        }
    }

    private interface WeatherService {
        @GET("current.json")
        suspend fun getCurrentWeather(
            @Query("key") apiKey: String,
            @Query("q") zipCode: String,
            @Query("aqi") aqi: String = "no"
        ): retrofit2.Response<WeatherResponse>
    }

    private fun createWeatherApi(): WeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
}
