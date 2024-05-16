package com.example.pinchgesture

import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.app.AppCompatActivity
import com.example.pinchgesture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding
        setContentView(binding.root)

        setupPinchZoom()
        updateText()
    }

    private fun setupPinchZoom() {
        scaleGestureDetector = ScaleGestureDetector(this, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scaleFactor *= detector.scaleFactor

                // Prevent the scale factor from getting too small or too large
                scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f))

                binding.bot.scaleX = scaleFactor
                binding.bot.scaleY = scaleFactor

                updateText()

                return true
            }
        })
    }

    private fun updateText() {
        // Format the scale factor to two decimal places and display it in the TextView
        binding.scale.text = "Scale Factor: ${String.format("%.2f", scaleFactor)}"
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        return true
    }



}
