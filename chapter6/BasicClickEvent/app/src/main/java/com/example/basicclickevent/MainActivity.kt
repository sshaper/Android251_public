package com.example.basicclickevent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.basicclickevent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            binding.output.text = "Kotlin Button Clicked"
        }
    }

    fun onButtonClick(view: View) {
        binding.output.text = "XML Button Clicked"
    }

}