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

class MainActivity : AppCompatActivity() {

    private var musicService: IMusicService? = null
    private var isBound = false
    private lateinit var binding: ActivityMainBinding
    private lateinit var handler: Handler
    private lateinit var updateUITask: Runnable

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            musicService = IMusicService.Stub.asInterface(service)
            isBound = true
            updateUI()
            handler.post(updateUITask)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            handler.removeCallbacks(updateUITask)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { musicService?.playMusic() }
        binding.pauseButton.setOnClickListener { musicService?.pauseMusic() }
        binding.stopButton.setOnClickListener { musicService?.stopMusic() }

        handler = Handler(Looper.getMainLooper())
        updateUITask = Runnable { updateUI() }

        // Bind to the MusicService
        Intent().setComponent(ComponentName("com.example.remotemusicplayer", "com.example.remotemusicplayer.MusicService")).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
        handler.removeCallbacks(updateUITask)
    }

    private fun updateUI() {
        if (isBound) {
            binding.trackName.text = musicService?.currentTrack
            binding.playTime.text = formatTime(musicService?.playTime ?: 0)
            handler.postDelayed(updateUITask, 1000) // Update every second
        }
    }

    private fun formatTime(milliseconds: Int): String {
        val seconds = milliseconds / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}
