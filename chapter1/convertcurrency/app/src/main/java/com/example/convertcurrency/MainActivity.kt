package com.example.convertcurrency
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components using findViewById
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // Set up the button click listener
        btnConvert.setOnClickListener {
            // Get the amount entered in the EditText, convert it to a Double, and handle possible null values
            val usdAmount = etAmount.text.toString().toDoubleOrNull()
            if (usdAmount != null) {
                // Convert the USD amount to Euros at a conversion rate (example rate is 0.93)
                val euroAmount = usdAmount * 0.93 // Example conversion rate
                val formatter = DecimalFormat("#,###.00") // Format to two decimal places
                tvResult.text = "€${formatter.format(euroAmount)}" // Display the formatted result in the TextView

            } else {
                // Inform the user if the input was not a valid number
                tvResult.text = "Please enter a valid amount"

            }
        }
    }
}