package com.example.statechangeexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val logTag = "StateChange"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(logTag, "onCreate called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(logTag, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(logTag, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(logTag, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(logTag, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(logTag, "onRestart called")
    }
}