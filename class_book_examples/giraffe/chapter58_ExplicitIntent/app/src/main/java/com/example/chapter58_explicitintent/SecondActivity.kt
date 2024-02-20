package com.example.chapter58_explicitintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import com.example.chapter58_explicitintent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras ?: return
        val qString = extras.getString("qString")
        binding.textView2.text = qString
    }

    fun returnText(view: View){
        finish()
    }

    override fun finish(){
        val data = Intent()

        val returnString = binding.editText2.text.toString()
        data.putExtra("returnData",returnString)

        //HERE YOU COULD HAVE SOME LOGIC THAT IF THE DATA IS GOOD THEN RESULT_OK BUT IF NOT THEN RESULT_CANCELED
        setResult(RESULT_OK, data)
        super.finish()

    }
}