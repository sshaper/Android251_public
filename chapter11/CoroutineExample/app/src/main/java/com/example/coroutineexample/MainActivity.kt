package com.example.coroutineexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import com.example.coroutineexample.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

/**
 * MainActivity implements a simple timer application using Kotlin Coroutines.
 * It demonstrates the usage of coroutines for handling background tasks and UI updates.
 */
class MainActivity : AppCompatActivity() {
    // View binding instance to access UI elements
    private lateinit var binding: ActivityMainBinding
    // Coroutine job to manage the timer coroutine
    private var job: Job? = null
    // Counter to track elapsed time in seconds
    private var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listeners for timer controls
        binding.startButton.setOnClickListener{ startTimer()}
        binding.stopButton.setOnClickListener { stopTimer() }
        binding.resetButton.setOnClickListener { resetTimer() }
    }

    /**
     * Starts the timer using a coroutine running on the Main dispatcher.
     * The timer updates every second and displays the elapsed time.
     */
    private fun startTimer() {
        // Only start if no active job exists
        if (job == null || job?.isActive != true) {
            job = CoroutineScope(Dispatchers.Main).launch {
                while (isActive) {
                    time++
                    binding.timeView.text = formatTime(time)
                    delay(1000) // Pause for 1 second
                }
            }
        }
    }

    /**
     * Stops the timer by canceling the coroutine job.
     */
    private fun stopTimer() {
        job?.cancel()
    }

    /**
     * Resets the timer to zero and updates the display.
     */
    private fun resetTimer() {
        stopTimer()
        time = 0L
        binding.timeView.text = "00:00:00"
    }

    /**
     * Formats the elapsed time into a readable string (HH:MM:SS).
     * @param seconds Total number of seconds elapsed
     * @return Formatted time string in HH:MM:SS format
     */
    private fun formatTime(seconds: Long): String {
        val hours = TimeUnit.SECONDS.toHours(seconds)
        val minutes = TimeUnit.SECONDS.toMinutes(seconds) % 60
        val sec = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, sec)
    }
}
