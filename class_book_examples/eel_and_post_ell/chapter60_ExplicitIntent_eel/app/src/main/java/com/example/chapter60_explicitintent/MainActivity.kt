package com.example.chapter60_explicitintent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
        i.putExtra("myname","scott")

        //startActivity IS A ONE WAY STREET AND DOES NOT NEED THE
        //REQUEST_CODE
        //startActivityForResult(i, request_code)
        startForResult.launch(i)
    }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            data?.let {
                if (it.hasExtra("returnData")) {
                    val returnString = it.extras?.getString("returnData")
                    binding.textView1.text = returnString
                }
            }
        }
    }

/*OLD WAY CURRENTLY IN BOOK
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
    }*/
}