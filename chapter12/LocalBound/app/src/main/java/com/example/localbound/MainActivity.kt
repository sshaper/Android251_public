package com.example.localbound

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.localbound.databinding.ActivityMainBinding

/**
 * MainActivity demonstrates a bound service pattern in Android.
 * It creates a connection to MusicService to control music playback
 * and displays the current track information and playback time.
 */
class MainActivity : AppCompatActivity() {

    // Reference to the bound MusicService
    private var musicService: MusicService? = null
    // Flag to track if we're currently bound to the service
    private var isBound = false
    // View binding for the activity's layout
    private lateinit var binding: ActivityMainBinding
    // Handler for updating UI on the main thread
    private val handler = Handler(Looper.getMainLooper())

    /**
     * ServiceConnection implementation that handles the binding lifecycle
     * between the Activity and MusicService
     */
    private val connection = object : ServiceConnection {
        // Called when the connection to the service is established
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
            isBound = true
            updateUI()
        }

        // Called when the connection to the service is lost
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listeners for music control buttons
        binding.playButton.setOnClickListener { musicService?.playMusic() }
        binding.pauseButton.setOnClickListener { musicService?.pauseMusic() }
        binding.stopButton.setOnClickListener { musicService?.stopMusic() }
    }

    /**
     * Called when the activity becomes visible to the user.
     * Binds to the MusicService to establish connection.
     */
    override fun onStart() {
        super.onStart()
        Intent(this, MusicService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    /**
     * Called when the activity is no longer visible to the user.
     * Unbinds from the service and cleans up resources.
     */
    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
        handler.removeCallbacks(updateUITask)
    }

    /**
     * Updates the UI with current track information and playback time.
     * Schedules itself to run every second to keep the display current.
     */
    private fun updateUI() {
        if (isBound) {
            binding.trackName.text = musicService?.getCurrentTrack()
            binding.playTime.text = formatTime(musicService?.getPlayTime() ?: 0)
            handler.postDelayed(updateUITask, 1000)
        }
    }

    // Runnable task to update UI periodically
    private val updateUITask = Runnable { updateUI() }

    /**
     * Formats milliseconds into a MM:SS display format
     * @param milliseconds The time in milliseconds to format
     * @return Formatted time string in MM:SS format
     */
    private fun formatTime(milliseconds: Int): String {
        val seconds = milliseconds / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}

