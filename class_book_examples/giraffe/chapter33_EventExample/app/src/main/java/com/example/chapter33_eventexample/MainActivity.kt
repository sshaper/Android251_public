package com.example.chapter33_eventexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.chapter33_eventexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.myButton.setOnClickListener {
            binding.statusText.text = getText(R.string.buttonClick)

        }

        binding.myButton.setOnLongClickListener {
            binding.statusText.text = getText(R.string.buttonLongClick)
            false

        }
    }
}