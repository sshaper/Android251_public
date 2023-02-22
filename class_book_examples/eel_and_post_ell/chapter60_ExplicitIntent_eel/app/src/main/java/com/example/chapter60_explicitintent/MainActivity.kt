package com.example.chapter60_explicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import com.example.chapter60_explicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val request_code = 5
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun sendText(view: View) {

        val i = Intent(this, SecondActivity::class.java)

        val myString = binding.editText1.text.toString()

        i.putExtra("qString", myString)

        //I ADDED THIS ONE
        i.putExtra("tag","scott")

        //startActivity IS A ONE WAY STREET AND DOES NOT NEED THE
        //REQUEST_CODE
        startActivityForResult(i, request_code)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == request_code) && (resultCode == RESULT_OK)) {

            data?.let {
                if (it.hasExtra("returnData")) {
                    val returnString = it.extras?.getString("returnData")
                    binding.textView1.text = returnString
                }
            }
        }
    }
}