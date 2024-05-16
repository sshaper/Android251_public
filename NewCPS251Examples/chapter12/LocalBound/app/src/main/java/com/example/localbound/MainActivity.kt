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

class MainActivity : AppCompatActivity() {

    private var musicService: MusicService? = null
    private var isBound = false
    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MusicService.MusicBinder
            musicService = binder.getService()
            isBound = true
            updateUI()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { musicService?.playMusic() }
        binding.pauseButton.setOnClickListener { musicService?.pauseMusic() }
        binding.stopButton.setOnClickListener { musicService?.stopMusic() }

        // Bind to the service
        Intent(this, MusicService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MusicService::class.java).also { intent ->
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
            binding.trackName.text = musicService?.getCurrentTrack()
            binding.playTime.text = formatTime(musicService?.getPlayTime() ?: 0)
            handler.postDelayed(updateUITask, 1000)
        }
    }

    private val updateUITask = Runnable { updateUI() }

    private fun formatTime(milliseconds: Int): String {
        val seconds = milliseconds / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}

