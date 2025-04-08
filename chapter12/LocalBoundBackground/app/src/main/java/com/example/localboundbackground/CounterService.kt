package com.example.localboundbackground

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat

/**
 * A foreground service that maintains a counter and updates it every second.
 * This service continues running even when the app is in the background or the device is rotated.
 */
class CounterService : Service() {
    // Binder object that allows the Activity to communicate with this service
    private val binder = CounterBinder()
    
    // The current counter value
    private var counter = 0
    
    // Handler for scheduling counter updates on the main thread
    private val handler = Handler(Looper.getMainLooper())
    
    // Flag to track if counting is active
    private var isCounting = false
    
    // Callback interface for communicating with the Activity
    private var callback: CounterCallback? = null
    
    // Notification channel and ID for the foreground service
    private val CHANNEL_ID = "CounterServiceChannel"
    private val NOTIFICATION_ID = 1

    /**
     * Interface for the Activity to receive counter updates
     */
    interface CounterCallback {
        fun onCounterUpdated(count: Int)
    }

    /**
     * Binder class that provides access to this service instance
     */
    inner class CounterBinder : Binder() {
        fun getService(): CounterService = this@CounterService
    }

    /**
     * Runnable that increments the counter and updates the UI
     */
    private val runnable = object : Runnable {
        override fun run() {
            if (isCounting) {
                counter++
                // Notify the Activity of the new count
                callback?.onCounterUpdated(counter)
                // Update the notification
                updateNotification()
                // Schedule the next update in 1 second
                handler.postDelayed(this, 1000)
            }
        }
    }

    /**
     * Called when the service is first created
     */
    override fun onCreate() {
        super.onCreate()
        // Set up the notification channel
        createNotificationChannel()
        // Start the service in the foreground with a notification
        startForeground(NOTIFICATION_ID, createNotification())
    }

    /**
     * Called when a component binds to this service
     */
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    /**
     * Called when the service is started
     * Returns START_STICKY to ensure the service restarts if killed
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    /**
     * Starts the counter if it's not already running
     */
    fun startCounting() {
        if (!isCounting) {
            isCounting = true
            handler.post(runnable)
        }
    }

    /**
     * Stops the counter and resets it to 0
     */
    fun stopAndResetCounter() {
        isCounting = false
        counter = 0
        callback?.onCounterUpdated(counter)
        updateNotification()
    }

    /**
     * Sets the callback for counter updates
     */
    fun setCallback(callback: CounterCallback) {
        this.callback = callback
        callback.onCounterUpdated(counter)
    }

    /**
     * Creates the notification channel for Android 8.0 and above
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Counter Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    /**
     * Creates a notification for the foreground service
     */
    private fun createNotification(): Notification {
        // Create an intent to open the app when the notification is tapped
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Counter Service")
            .setContentText("Counter: $counter")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .build()
    }

    /**
     * Updates the notification with the current counter value
     */
    private fun updateNotification() {
        val notification = createNotification()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    /**
     * Called when the service is destroyed
     */
    override fun onDestroy() {
        super.onDestroy()
        // Stop the counter updates
        handler.removeCallbacks(runnable)
    }
} 