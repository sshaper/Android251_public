package com.example.conversionprojectLiveData

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.conversionprojectLiveData.databinding.ActivityMainBinding
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Observe the LiveData from the ViewModel
        viewModel.formattedCurrency.observe(this, Observer { formattedCurrency ->
            binding.tvResult.text = formattedCurrency
        })

        binding.btnConvert.setOnClickListener {
            // Get the user input and convert it
            val amount = binding.etAmount.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            viewModel.convertCurrency(amount)
        }
    }
}
