package com.example.chapter58_explicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import com.example.chapter58_explicitintent.databinding.ActivityMainBinding

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //THE VARIABLE NAME CAN BE ANYTHING THAT IS ASSIGNED TO REGISTERFORACTIVITYRESULT
    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            data?.let {
                if (it.hasExtra("returnData")) {
                    val returnString = it.extras?.getString("returnData")
                    binding.textView1.text = returnString
                }
            }
        }
    }

    fun sendText(view: View){
        val i = Intent(this, SecondActivity::class.java)
        val myString = binding.editText1.text.toString()
        i.putExtra("qString",myString)
        //startActivity(i)
        startForResult.launch(i)
    }
}