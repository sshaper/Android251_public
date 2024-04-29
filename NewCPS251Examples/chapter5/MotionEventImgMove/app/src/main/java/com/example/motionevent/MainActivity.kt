package com.example.motionevent
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.motionevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dX: Float = 0f
    private var dY: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding
        setContentView(binding.root)

        setupTouchListener()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchListener() {
        binding.bot.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Remember the initial position when the screen is first touched
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    // Update the position of the ImageView
                    view.animate()
                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()

                    // Update the coordinates in the TextView
                    updateCoordinates(view.x, view.y)
                }
            }
            true // Return true to indicate that the MotionEvent has been consumed and should not be propagated further.
        }
    }

    private fun updateCoordinates(x: Float, y: Float) {
        // Format the coordinates to two decimal places and display them
        binding.coordinates.text = "X: ${"%.2f".format(x)}, Y: ${"%.2f".format(y)}"
    }
}
