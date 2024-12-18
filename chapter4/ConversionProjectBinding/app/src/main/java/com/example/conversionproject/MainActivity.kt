package com.example.conversionproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import com.example.conversionproject.databinding.ActivityMainBinding // Import the generated binding class

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding
        setContentView(binding.root) // Set the content view to the root of the binding

        // Set up the button click listener using view binding
        binding.btnConvert.setOnClickListener {
            // Get the amount entered in the EditText, convert it to a Double, and handle possible null values
            val usdAmount = binding.etAmount.text.toString().toDoubleOrNull()
            if (usdAmount != null) {
                // Convert the USD amount to Euros at a conversion rate (example rate is 0.93)
                val euroAmount = usdAmount * 0.93 // Example conversion rate
                val formatter = DecimalFormat("#,###.00") // Format to two decimal places
                binding.tvResult.text = "€${formatter.format(euroAmount)}" // Display the formatted result in the TextView
            } else {
                // Inform the user if the input was not a valid number
                binding.tvResult.text = getString(R.string.error)
            }
        }
    }
}