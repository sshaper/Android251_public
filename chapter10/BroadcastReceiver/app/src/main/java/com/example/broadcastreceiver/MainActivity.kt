package com.example.broadcastreceiver

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraManager: CameraManager
    private val torchCallback = object : CameraManager.TorchCallback() {
        override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {
            showToast(if (enabled) "Flashlight is ON" else "Flashlight is OFF")
            if (enabled) {
                Log.i("Flashlight", "Flashlight is ON")
            }
            else {
                Log.i("Flashlight", "Flashlight is OFF")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        registerTorchCallback()
        registerPowerReceiver()
    }

    private fun registerTorchCallback() {
        try {
            cameraManager.registerTorchCallback(torchCallback, null)
        } catch (e: CameraAccessException) {
            showToast("Failed to access camera for flashlight status.")
        }
    }

    private fun registerPowerReceiver() {
        IntentFilter(Intent.ACTION_POWER_CONNECTED).apply {
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }.also { filter ->
            registerReceiver(BroadcastReceiver(), filter)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraManager.unregisterTorchCallback(torchCallback)
    }
}

