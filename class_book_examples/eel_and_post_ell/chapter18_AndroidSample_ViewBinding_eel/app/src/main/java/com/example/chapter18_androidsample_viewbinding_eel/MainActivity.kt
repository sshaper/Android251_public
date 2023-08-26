package com.example.chapter18_androidsample_viewbinding_eel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.chapter18_androidsample_viewbinding_eel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WE DON'T NEED THIS CALLED BELOW
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun convertCurrency() {

        if (binding.dollarText.text.isNotEmpty()) {

            val dollarValue = binding.dollarText.text.toString().toFloat()

            val euroValue = dollarValue * 0.85f

            binding.textView.text = euroValue.toString()
        } else {
            binding.textView.text = getString(R.string.no_value_string)
        }
    }
}
