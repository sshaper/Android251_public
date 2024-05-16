package com.example.coroutineexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import com.example.coroutineexample.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var job: Job? = null
    private var time = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{ startTimer()}
        binding.stopButton.setOnClickListener { stopTimer() }
        binding.resetButton.setOnClickListener { resetTimer() }
    }

    private fun startTimer() {
        if (job == null || job?.isActive != true) {
            job = CoroutineScope(Dispatchers.Main).launch {
                while (isActive) {
                    time++
                    binding.timeView.text = formatTime(time)
                    delay(1000)
                }
            }
        }
    }

    private fun stopTimer() {
        job?.cancel()
    }

    private fun resetTimer() {
        stopTimer()
        time = 0L
        binding.timeView.text = "00:00:00"
    }

    private fun formatTime(seconds: Long): String {
        val hours = TimeUnit.SECONDS.toHours(seconds)
        val minutes = TimeUnit.SECONDS.toMinutes(seconds) % 60
        val sec = seconds % 60
        return String.format("%02d:%02d:%02d", hours, minutes, sec)
    }
}
