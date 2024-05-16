package com.example.explicitintent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.explicitintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

       
        val dataReceived = intent.getStringExtra("input")
        binding.textViewReceived.text = dataReceived ?: "No data received"

        binding.buttonReturn.setOnClickListener {
            val returnIntent = Intent().apply {
                putExtra("response", "Response from SecondActivity")
            }
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}