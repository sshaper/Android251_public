package com.example.addname

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.addname.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize ViewModel using ViewModelProvider
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Bind ViewModel to layout
        binding.viewModel = viewModel

        // Set lifecycle owner to enable LiveData updates in the UI
        binding.lifecycleOwner = this
    }
}