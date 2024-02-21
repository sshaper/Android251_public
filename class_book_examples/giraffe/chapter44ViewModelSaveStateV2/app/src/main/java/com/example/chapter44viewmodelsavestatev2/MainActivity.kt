package com.example.chapter44viewmodelsavestatev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.SavedStateViewModelFactory
import com.example.chapter44viewmodelsavestatev2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this?.application?.let {
            val factory = SavedStateViewModelFactory(it, this)

            viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        }


        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding = ActivityMainBinding.inflate(layoutInflater)

        //binding.resultText.text = viewModel.getResult().toString()




        val resultObserver = Observer<Float>{
            result -> binding.resultText.text = result.toString()
        }

        viewModel.getResult().observe(this,resultObserver)


        binding.convertButton.setOnClickListener{
            if (binding.dollarText.text.isNotEmpty()) {
                viewModel.setAmount(binding.dollarText.text.toString())
                //binding.resultText.text = viewModel.getResult().toString()
            } else {
                binding.resultText.text = "No Value"
            }
        }
        setContentView(binding.root)
    }



}