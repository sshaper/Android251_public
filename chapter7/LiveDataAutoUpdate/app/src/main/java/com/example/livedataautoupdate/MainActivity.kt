package com.example.livedataautoupdate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedataautoupdate.databinding.ActivityMainBinding

/**
 * MainActivity that displays a changing name every second using LiveData.
 */
class MainActivity : AppCompatActivity() {

    // View binding for accessing UI elements
    private lateinit var binding: ActivityMainBinding

    // ViewModel instance to manage UI-related data
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observe LiveData from ViewModel and update the TextView whenever data changes
        viewModel.currentName.observe(this, Observer { name ->
            binding.output.text = name  // Update TextView with the latest name
        })
    }
}
