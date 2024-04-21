package com.example.coroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val myCoroutineScope = CoroutineScope(Dispatchers.Main)

    var number = 0

    val names = arrayListOf<String>("Scott", "Karen", "Scottie", "Jazzy", "Oreo","Margaret","Jim","Danny","Michelle","Greg","Jackie","Jack","Tyson")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartNames.setOnClickListener {

            displayNamesNotBlocking()
            //displayNamesBlocking()
        }

        binding.btnDisplayNumber.setOnClickListener {
            number++
            binding.showNumber.text = number.toString()
        }
    }

    //THIS IS A COROUTINE BUT IT IS BLOCKING THE MAIN THREAD
    fun displayNamesBlocking() = runBlocking{
        val num = (0..(names.size - 1)).random()
        binding.displayName.text = names[num]
        Thread.sleep(5000)

    }


    fun displayNamesNotBlocking() {
        val num = (0..(names.size - 1)).random()

        binding.displayName.text = names[num]
        myCoroutineScope.launch(Dispatchers.Main) {
            pause()
        }
    }

    suspend fun pause(){
        delay(1500)
        displayNamesNotBlocking()
    }




}