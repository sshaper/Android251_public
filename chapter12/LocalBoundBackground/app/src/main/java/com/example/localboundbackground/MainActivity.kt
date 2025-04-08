package com.example.localboundbackground

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.localboundbackground.databinding.ActivityMainBinding

/**
 * Main activity that displays the counter and controls the CounterService.
 * Implements CounterCallback to receive counter updates from the service.
 */
class MainActivity : AppCompatActivity(), CounterService.CounterCallback {
    // Reference to the bound service
    private var counterService: CounterService? = null
    
    // Flag to track if we're bound to the service
    private var bound = false
    
    // View binding for accessing views
    private lateinit var binding: ActivityMainBinding

    // Permission request launcher for notifications
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, start the service
            startCounterService()
        } else {
            Toast.makeText(this, "Notification permission is required for the counter service", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * ServiceConnection implementation that handles binding to the CounterService
     */
    private val connection = object : ServiceConnection {
        /**
         * Called when the service is connected
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            // Get the service instance from the binder
            val binder = service as CounterService.CounterBinder
            counterService = binder.getService()
            bound = true
            // Set up the callback to receive counter updates
            counterService?.setCallback(this@MainActivity)
        }

        /**
         * Called when the service is disconnected
         */
        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }
    }

    /**
     * Called when the activity is created
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Start the service
        startService()
        
        // Set up button click listeners
        binding.startButton.setOnClickListener {
            counterService?.startCounting()
        }

        binding.resetButton.setOnClickListener {
            counterService?.stopAndResetCounter()
        }
    }

    /**
     * Called when the counter value is updated by the service
     */
    override fun onCounterUpdated(count: Int) {
        binding.counterText.text = count.toString()
    }

    /**
     * Called when the activity becomes visible
     */
    override fun onStart() {
        super.onStart()
        // Bind to the service
        startCounterService()
    }

    /**
     * Called when the activity is no longer visible
     */
    override fun onStop() {
        super.onStop()
        // Unbind from the service
        if (bound) {
            unbindService(connection)
            bound = false
        }
    }

    private fun startService() {
        // Check if we need to request notification permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Permission already granted, start the service
                    startCounterService()
                }
                else -> {
                    // Request the permission
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            // No need for permission on older Android versions
            startCounterService()
        }
    }

    private fun startCounterService() {
        val intent = Intent(this, CounterService::class.java)
        startService(intent)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }
}