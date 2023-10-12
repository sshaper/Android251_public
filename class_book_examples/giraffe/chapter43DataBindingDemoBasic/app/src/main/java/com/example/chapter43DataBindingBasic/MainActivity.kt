package com.example.chapter43DataBindingBasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import com.example.chapter43DataBindingBasic.databinding.ActivityMainBinding
import com.example.chapter43DataBindingBasic.BR.myViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //THIS WON'T WORK IT IS NOT A FRAGMENT
        //binding = DataBindingUtil.inflate(inflater, R.layout.ac, container, false)

        //I HAD TO USE THIS FOR THE MAIN ACTIVITY
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.lifecycleOwner = this
        binding.setVariable(myViewModel, viewModel)

        //binding = ActivityMainBinding.inflate(layoutInflater)

        //binding.resultText.text = viewModel.getResult().toString()


        /*val resultObserver = Observer<Float>{
                result -> binding.resultText.text = result.toString()
        }

       //THIS IS DIFFERENT THAN WHAT THE BOOK HAS I BELIEVE IT IS BECAUSE I AM NOT USING A FRAGMENT
        viewModel.getResult().observe(this,resultObserver)

        binding.convertButton.setOnClickListener{
            if (binding.dollarText.text.isNotEmpty()) {
                viewModel.setAmount(binding.dollarText.text.toString())
                //binding.resultText.text = viewModel.getResult().toString()
            } else {
                binding.resultText.text = "No Value"
            }
        }*/

        setContentView(binding.root)
    }



}