package com.example.chapter44_lifecycleobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.chapter44_lifecycleobserver.DemoObserver
import com.example.chapter44_lifecycleobserver.DemoOwner

class MainActivity : AppCompatActivity() {

    //private lateinit var demoOwner: DemoOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(DemoObserver())

        //demoOwner = DemoOwner()
        //demoOwner.startOwner()
        //demoOwner.stopOwner()

    }


}