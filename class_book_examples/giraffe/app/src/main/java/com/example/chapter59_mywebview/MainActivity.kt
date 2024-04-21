package com.example.chapter59_mywebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.net.URL
import com.example.chapter59_mywebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        handleIntent()
    }

    private fun handleIntent(){
        val intent = this.intent
        val data = intent.data
        var url: URL? = null
        try {
            url = URL(data?.scheme,
                data?.host,
                data?.path)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.webView.loadUrl(url.toString())




    }
}