package com.example.chapter18_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

//import android.widget.EditText
//import android.widget.TextView
import com.example.chapter18_viewbinding.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun convertCurrency(view: View) {
        //val dollarText: EditText = findViewById(R.id.dollarText)
        //val textView: TextView = findViewById(R.id.outputText)


        if (binding.dollarText.text.isNotEmpty()) {
            val dollarValue = binding.dollarText.text.toString().toFloat()
            val euroValue = dollarValue * 0.85f
            binding.outputText.text = euroValue.toString()
        } else {
            binding.outputText.text = getString(R.string.no_value_string)
        }
    }
}