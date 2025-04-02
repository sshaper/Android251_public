package com.example.localbound

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

/**
 * MusicService is a bound service that handles music playback.
 * It provides methods to control music playback (play, pause, stop)
 * and retrieve information about the current track and playback time.
 */
class MusicService : Service() {

    // Binder instance that will be returned to clients
    private val binder = MusicBinder()
    // MediaPlayer instance to handle music playback
    private var mediaPlayer: MediaPlayer? = null
    // Name of the current track being played
    private val currentTrack = "Sample Track"

    /**
     * Called when a client binds to the service.
     * @param intent The Intent that was used to bind to this service
     * @return IBinder instance that clients can use to interact with the service
     */
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    /**
     * Inner class that provides a way for clients to get a reference to the service.
     * This is the standard pattern for bound services in Android.
     */
    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    /**
     * Starts playing the music track.
     * If MediaPlayer is not initialized, it creates a new instance
     * and sets it to loop continuously.
     */
    fun playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.music)
            mediaPlayer?.isLooping = true
        }
        mediaPlayer?.start()
    }

    /**
     * Pauses the currently playing music.
     */
    fun pauseMusic() {
        mediaPlayer?.pause()
    }

    /**
     * Stops the music playback and releases the MediaPlayer resources.
     */
    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    /**
     * Returns the name of the current track.
     * @return String containing the track name
     */
    fun getCurrentTrack(): String {
        return currentTrack
    }

    /**
     * Returns the current playback position in milliseconds.
     * @return Current position in milliseconds, or 0 if no music is playing
     */
    fun getPlayTime(): Int {
        return mediaPlayer?.currentPosition ?: 0
    }

    /**
     * Called when the service is being destroyed.
     * Cleans up resources by releasing the MediaPlayer.
     */
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
