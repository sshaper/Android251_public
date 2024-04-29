package com.example.fragmentxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),Fragment1.OnMessageSendListener, Fragment2.OnMessageSendListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding
        setContentView(binding.root)
    }
    override fun onMessageSend1(message: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? Fragment2
        fragment?.updateMessage(message)
    }

    override fun onMessageSend2(message: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView1) as? Fragment1
        fragment?.updateMessage(message)
    }
}