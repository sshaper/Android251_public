package com.example.remotemusicplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

/**
 * MusicService is a remote service that handles music playback functionality.
 * It can be accessed by other applications through AIDL (Android Interface Definition Language).
 * This service manages a MediaPlayer instance and provides basic music control operations.
 */
class MusicService : Service() {

    // MediaPlayer instance to handle actual music playback
    private var mediaPlayer: MediaPlayer? = null
    // Name of the current track being played
    private val _currentTrack = "Sample Track"

    /**
     * Binder implementation that provides the interface for remote clients to interact with the service.
     * Implements IMusicService.Stub which is the server-side implementation of the AIDL interface.
     */
    private val binder = object : IMusicService.Stub() {
        /**
         * Starts playing the music. If MediaPlayer is not initialized, it creates a new instance
         * and sets up the music resource. The music will loop continuously.
         */
        override fun playMusic() {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this@MusicService, R.raw.music)
                mediaPlayer?.isLooping = true
            }
            mediaPlayer?.start()
            Log.d("MusicService", "Music started")
        }

        /**
         * Pauses the currently playing music without releasing resources.
         */
        override fun pauseMusic() {
            mediaPlayer?.pause()
            Log.d("MusicService", "Music paused")
        }

        /**
         * Stops the music playback and releases the MediaPlayer resources.
         * The MediaPlayer instance is set to null after release.
         */
        override fun stopMusic() {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            Log.d("MusicService", "Music stopped and MediaPlayer released")
        }

        /**
         * Returns the name of the current track being played.
         * @return String representing the current track name
         */
        override fun getCurrentTrack(): String {
            return _currentTrack
        }

        /**
         * Returns the current playback position in milliseconds.
         * @return Int representing the current position in milliseconds
         */
        override fun getPlayTime(): Int {
            return mediaPlayer?.currentPosition ?: 0
        }
    }

    /**
     * Called when a client binds to the service using bindService().
     * @param intent The Intent that was used to bind to this service
     * @return IBinder interface for clients to interact with the service
     */
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    /**
     * Called when the service is being destroyed. Ensures proper cleanup of resources
     * by releasing the MediaPlayer instance.
     */
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
        Log.d("MusicService", "Service destroyed and MediaPlayer released")
    }
}
