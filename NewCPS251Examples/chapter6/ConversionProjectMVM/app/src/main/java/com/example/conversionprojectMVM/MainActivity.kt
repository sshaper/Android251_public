package com.example.conversionprojectMVM


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.conversionprojectMVM.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //This is called to update the textview after device is rotated.
        binding.tvResult.text = viewModel.getFormattedCurrency()

        binding.btnConvert.setOnClickListener {
            // Trigger the conversion
            viewModel.convertCurrency(binding.etAmount.text.toString().toDouble())
            // Update the TextView directly from ViewModel
            binding.tvResult.text = viewModel.getFormattedCurrency()
        }
    }
}