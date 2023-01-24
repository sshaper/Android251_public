package com.example.chapter46_lifecycledemo_eel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class DemoOwner: LifecycleOwner {

    private val lifecycleRegistry: LifecycleRegistry

    init {
        lifecycleRegistry = LifecycleRegistry(this)

        //I ADDED THE CUSTOMOBSERVER JUST TO SHOW WHAT CAN BE DONE WITH ANOTHER ONE.  
        lifecycle.addObserver(DemoObserver())
        //lifecycle.addObserver(CustomObserver())
    }

    fun startOwner() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun stopOwner() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }
    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

}