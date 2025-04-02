package com.example.musicplayerclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayerclient.databinding.ActivityMainBinding
import com.example.remotemusicplayer.IMusicService

/**
 * MainActivity for the MusicPlayerClient app.
 * This activity provides the user interface for controlling the remote music service.
 * It connects to the MusicService running in the RemoteMusicPlayer app using AIDL
 * and provides controls for playing, pausing, and stopping music.
 */
class MainActivity : AppCompatActivity() {

    // Reference to the remote music service interface
    private var musicService: IMusicService? = null
    // Flag indicating if the activity is bound to the service
    private var isBound = false
    // View binding for the activity's layout
    private lateinit var binding: ActivityMainBinding
    // Handler for updating the UI periodically
    private lateinit var handler: Handler
    // Runnable task for updating the UI
    private lateinit var updateUITask: Runnable

    /**
     * ServiceConnection implementation to handle binding to the remote service.
     * Manages the lifecycle of the connection to the MusicService.
     */
    private val connection = object : ServiceConnection {
        /**
         * Called when the connection to the service is established.
         * Sets up the service interface and starts UI updates.
         */
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            musicService = IMusicService.Stub.asInterface(service)
            isBound = true
            updateUI()
            handler.post(updateUITask)
        }

        /**
         * Called when the connection to the service is lost.
         * Cleans up resources and stops UI updates.
         */
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            handler.removeCallbacks(updateUITask)
        }
    }

    /**
     * Called when the activity is first created.
     * Sets up the UI, initializes the handler, and binds to the remote service.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listeners for the control buttons
        binding.playButton.setOnClickListener { musicService?.playMusic() }
        binding.pauseButton.setOnClickListener { musicService?.pauseMusic() }
        binding.stopButton.setOnClickListener { musicService?.stopMusic() }

        // Initialize handler and UI update task
        handler = Handler(Looper.getMainLooper())
        updateUITask = Runnable { updateUI() }

        // Bind to the remote MusicService
        Intent().setComponent(ComponentName("com.example.remotemusicplayer", "com.example.remotemusicplayer.MusicService")).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    /**
     * Called when the activity is no longer visible to the user.
     * Unbinds from the service and stops UI updates.
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
     * This method is called periodically to keep the UI in sync with the service state.
     */
    private fun updateUI() {
        if (isBound) {
            binding.trackName.text = musicService?.currentTrack
            binding.playTime.text = formatTime(musicService?.playTime ?: 0)
            handler.postDelayed(updateUITask, 1000) // Update every second
        }
    }

    /**
     * Formats the playback time from milliseconds to a MM:SS format.
     * @param milliseconds The time in milliseconds to format
     * @return String representing the formatted time
     */
    private fun formatTime(milliseconds: Int): String {
        val seconds = milliseconds / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}
