package com.example.viewbindingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

//Because of the binding we don't need these imports anymore
//import android.widget.Button
//import android.widget.TextView

//This was added per the book
import com.example.viewbindingexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //This was added per the book
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //This is removed
        //setContentView(R.layout.activity_main)

        //This was added per the book
        //The following code is to “inflate” the view binding class so that we can access the root view within the
        //layout.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //OLDER CODE
        //val btn: Button = findViewById(R.id.btn)
        //val txtBx : TextView = findViewById(R.id.txtBx)

        // btn.setOnClickListener(View.OnClickListener {
        // txtBx.text = "Hello Class"
        // })


        binding.btn.setOnClickListener(View.OnClickListener {
            binding.txtBx.text = "Hello Class"
        })

    }
}