package com.example.implicitintent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonShare.setOnClickListener {
            val textToShare = binding.editTextText.text.toString()
            shareText(textToShare)
        }
    }

    private fun shareText(text: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        // Check if there is an app that can handle this intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Share via"))
        }
    }
}
