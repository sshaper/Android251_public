package com.example.chapter46_lifecycledemo_eel



import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.LifecycleOwner

//DEPRECATED USE DEFAULTLIFECYCLEOBSERVER
//class CustomObserver: LifecycleObserver  {

class DemoObserver: DefaultLifecycleObserver {

    private val LOG_TAG = "DemoObserver"

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i(LOG_TAG, "Demo Observer onResume")

    }

    override fun onPause(owner: LifecycleOwner){
        super.onPause(owner)
        Log.i(LOG_TAG, "Demo Observer onPause")
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.i(LOG_TAG, "Demo Observer onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i(LOG_TAG, "Demo Observer onStart")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i(LOG_TAG, "Demo Observer onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i(LOG_TAG, "Demo Observer onDestroy")
    }

    /* THESE ARE ALL DEPRECATED

     @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
     fun onResume() {
         Log.i(LOG_TAG, "Custom Observer onResume")
     }

     @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
     fun onPause() {
         Log.i(LOG_TAG, "Custom Observer onPause")
     }

     @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
     fun onCreate() {
         Log.i(LOG_TAG, "Custom Observer onCreate")
     }

     @OnLifecycleEvent(Lifecycle.Event.ON_START)
     fun onStart() {
         Log.i(LOG_TAG, "Custom Observer onStart")
     }

     @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
     fun onStop() {
         Log.i(LOG_TAG, "Custom Observer onStop")
     }

     @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
     fun onDestroy() {
         Log.i(LOG_TAG, "Custom Observer onDestroy")
     }

     @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
     fun onAny(owner: LifecycleOwner, event: Lifecycle.Event) {
         //Log.i(LOG_TAG, "Custom Observer" + owner.lifecycle.currentState.name)
     }*/

}