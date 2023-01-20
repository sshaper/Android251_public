package com.example.chapter41_viewmodeldemo_livedata_eel.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {
    private val rate = 0.74f
    private var dollarText = ""
    private var result: MutableLiveData<Float> = MutableLiveData()

    fun setAmount(value: String) {
        this.dollarText = value
        result.setValue(value.toFloat() * rate)

    }

    fun getResult(): MutableLiveData<Float> {
        return result
    }
}