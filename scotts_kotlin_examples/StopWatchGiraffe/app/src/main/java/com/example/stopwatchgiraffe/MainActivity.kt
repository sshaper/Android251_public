package com.example.stopwatchgiraffe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.stopwatchgiraffe.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var time = 0
    private var action = true
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.timeView.text = time.toString()

        //I ATTEMPTED TO CALL THE START METHOD USING THE ONCLICK FROM THE XML WHICH WOULD CALL START(VIEW:VIEW) BUT I FOUND THAT WHEN I ATTEMPTED TO CALL IT FROM INCREMENTSECOND IT GENERATED AN ERROR.
        binding.startButton.setOnClickListener {
            action = true
            start()
        }

        binding.stopButton.setOnClickListener{
            action = false
            //WHEN STOP IS CLICKED TIME MAY INCREASE ONE PREMATURELY THIS STOPS THAT (MOST OF THE TIME)
            time--
        }

        binding.resetButton.setOnClickListener {
            //I COULD ALSO SET ACTION TO FALSE AND IT WOULD TECHNICALLY WORK BUT THAT WOULD NOT CANCEL THE CO-ROUTINE SO INSTEAD I DID JOB?.CANCEL AS SHOWN BELOW

            //CANCELS THE JOB.
            job?.cancel()
            time = 0
            binding.timeView.text = time.toString()
        }
    }


    fun start() {
        binding.timeView.text = time.toString()
        //THIS HOLDS ON TO THE JOB OBJECT THAT IS RETURNED BY LAUNCH SO JOB CAN BE CANCELLED
        job = coroutineScope.launch(Dispatchers.Main) {
            incrementSecond()
        }
    }


    suspend fun incrementSecond(){
        if(action){
            delay(1000)
            time++
            start()
        }
    }
}