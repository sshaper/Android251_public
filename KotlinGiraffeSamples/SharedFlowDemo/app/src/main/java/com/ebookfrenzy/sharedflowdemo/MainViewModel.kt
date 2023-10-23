package com.ebookfrenzy.sharedflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel  : ViewModel() {

    private val TAG = "SharedFlowDemo"
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        sharedFlowInit()
    }

    private fun sharedFlowInit() {
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000)
                Log.i(TAG, "Emitting $i")
                _sharedFlow.emit(i)
            }
        }
    }
}