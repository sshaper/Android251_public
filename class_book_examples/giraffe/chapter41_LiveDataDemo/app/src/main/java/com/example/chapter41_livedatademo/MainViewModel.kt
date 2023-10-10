package com.example.chapter41_livedatademo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


class MainViewModel : ViewModel() {
    private val rate = 0.74f
    private var dollarText = ""
    //private var result: Float = 0f
    private var result: MutableLiveData<Float> = MutableLiveData()

    fun setAmount(value: String) {
        this.dollarText = value
        //result = value.toFloat() * rate
        result.value = value.toFloat() * rate
    }

    //fun getResult(): Float {
        //return result
    //}

    fun getResult(): MutableLiveData<Float>{
        return result
    }
}