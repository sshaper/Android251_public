package com.example.remotemusicplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private val _currentTrack = "Sample Track"

    private val binder = object : IMusicService.Stub() {
        override fun playMusic() {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this@MusicService, R.raw.music)
                mediaPlayer?.isLooping = true
            }
            mediaPlayer?.start()
            Log.d("MusicService", "Music started")
        }

        override fun pauseMusic() {
            mediaPlayer?.pause()
            Log.d("MusicService", "Music paused")
        }

        override fun stopMusic() {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            Log.d("MusicService", "Music stopped and MediaPlayer released")
        }

        override fun getCurrentTrack(): String {
            return _currentTrack
            //return "test"
        }

        override fun getPlayTime(): Int {
            return mediaPlayer?.currentPosition ?: 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
        Log.d("MusicService", "Service destroyed and MediaPlayer released")
    }
}
