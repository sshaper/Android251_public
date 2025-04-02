package com.example.remotemusicplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity for the RemoteMusicPlayer app.
 * This is a simple activity that serves as the entry point for the music service app.
 * It doesn't contain any UI controls as the actual music playback functionality
 * is handled by the MusicService, which can be accessed by other applications.
 */
class MainActivity : AppCompatActivity() {
    /**
     * Called when the activity is first created.
     * Sets up the basic layout for the activity.
     * @param savedInstanceState Bundle containing the activity's previously saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}