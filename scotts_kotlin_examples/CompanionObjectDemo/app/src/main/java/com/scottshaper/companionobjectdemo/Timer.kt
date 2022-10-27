package com.scottshaper.companionobjectdemo


import androidx.lifecycle.LifecycleObserver
import com.scottshaper.companionobjectdemo.ui.main.MainViewModel
import kotlinx.coroutines.*

//THE WHOLE POINT OF THE TIME IS TO DISPLAY AN INCREMENTED NUMBER EVERY SECOND IN A SENSE COUNTING SECONDS.  THIS IS JUST TO MIMIC SOMETHING THAT WOULD BE FEEDING INFORMATION TO THE MAIN VIEW MODEL


class Timer: LifecycleObserver {
    private val myCoroutineScope = CoroutineScope(Dispatchers.Main)
    var mv = MainViewModel

    fun startTimer(Seconds: Int) {
        myCoroutineScope.launch(Dispatchers.Main) {
            addSeconds(Seconds)
        }
    }

    suspend fun addSeconds(Seconds: Int){
        var sec = Seconds
        mv.addSeconds(sec)
        sec++
        delay(1000) // simulates long running task
        addSeconds(sec)
    }
}