package com.scottshaper.companionobjectdemo.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scottshaper.companionobjectdemo.Timer

class MainViewModel : ViewModel() {
    var btn = true
    //SINCE THE TIMER NEEDS TO COMMUNICATE WITH THE MAIN VIEW MODEL I NEED TO USE A STATIC FUNCTION SO IT DOES NOT HAVE TO MAKE AN INSTANCE OF THE TIMER CLASS.  THIS WOULD CAUSE TO INDEPENDENT OBJECTS ONE IN THE MAINVIEWMODEL AND ONE IN THE MAINFRAGMENT.  BOTH OBJECTS HAVE TO USE THE TIMER OBJECT SO ONE MAKES AN INSTANCE OF IT (MAIN FRAGMENT) AND THE OTHER (MAIN VIEW MODEL) HAS STATIC METHODS THE TIMER CAN CALL
    companion object{
        private var sec: MutableLiveData<Int> = MutableLiveData()

        fun addSeconds(Seconds: Int){
            sec.value = Seconds
        }
    }

    fun getSeconds(): MutableLiveData<Int>{
        return sec
    }
}