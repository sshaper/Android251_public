package com.example.restexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * MainActivity that demonstrates weather data fetching using Retrofit and Coroutines
 * This app allows users to enter a zip code and fetch current weather information
 */
class MainActivity : AppCompatActivity() {
    // View binding instance for accessing UI elements
    private lateinit var binding: ActivityMainBinding
    // Weather API service instance
    private val weatherApi = createWeatherApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listener for the fetch weather button
        binding.btnFetchWeather.setOnClickListener {
            val zipCode = binding.etZipCode.text.toString()
            if (zipCode.isNotEmpty()) {
                fetchWeather(zipCode)
            }
        }
    }

    /**
     * Fetches weather data for the given zip code using the WeatherAPI
     * Uses coroutines to perform network operation on IO thread and update UI on Main thread
     * param zipCode The zip code to fetch weather for
     */
    private fun fetchWeather(zipCode: String) {
        // Launch a coroutine in the IO dispatcher for network operations
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Make API call to get weather data
                val response = weatherApi.getCurrentWeather("3a32e73914444f0f818180726241305", zipCode)
                // Switch to Main thread to update UI
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        // Update temperature display
                        binding.tvTemp.text = "${response.body()!!.current.temp_f} °F"
                        // Update detailed weather information
                        binding.tvWeatherInfo.text = "Wind MPH ${response.body()!!.current.wind_mph}\nWind Direction ${response.body()!!.current.wind_dir}\nWind Gusts MPH ${response.body()!!.current.gust_mph}\nHumidity ${response.body()!!.current.humidity}%\nPrecipitation ${response.body()!!.current.precip_in}"
                    } else {
                        binding.tvWeatherInfo.text = "Failed to retrieve data."
                    }
                }
            } catch (e: Exception) {
                // Handle any errors and display them on the UI
                withContext(Dispatchers.Main) {
                    binding.tvWeatherInfo.text = "Error: ${e.message}"
                }
            }
        }
    }

    /**
     * Interface defining the Weather API service endpoints
     * Uses Retrofit annotations to define the API contract
     * 
     * This interface acts as a contract between your app and the Weather API.
     * Retrofit uses this interface to generate the actual HTTP requests.
     * The interface methods are annotated with Retrofit annotations that specify:
     * - The HTTP method (@GET)
     * - The endpoint path ("current.json")
     * - Query parameters (@Query)
     */
    private interface WeatherService {
        /**
         * Makes a GET request to fetch current weather data
         * 
         * @param apiKey The API key required for authentication with the weather service
         * @param zipCode The location to get weather for (in zip code format)
         * @param aqi Whether to include Air Quality Index data (defaults to "no")
         * @return A Response object containing the weather data wrapped in WeatherResponse
         * 
         * The @GET annotation specifies this is a GET request to "current.json"
         * The @Query annotations define URL query parameters that will be added to the request
         * The suspend keyword indicates this is a coroutine function that can be paused
         */
        @GET("current.json")
        suspend fun getCurrentWeather(
            @Query("key") apiKey: String,
            @Query("q") zipCode: String,
            @Query("aqi") aqi: String = "no"
        ): retrofit2.Response<WeatherResponse>
    }

    /**
     * Creates and configures the Retrofit instance for the Weather API
     * return Configured WeatherService instance
     */
    private fun createWeatherApi(): WeatherService {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }
}
