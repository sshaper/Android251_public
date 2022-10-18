package com.scottshaper.dolphintest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData


class MainViewModel : ViewModel() {
    private val rate = 0.74f
    //private var dollarText = ""
    //private var result: Float = 0f
    //private var result: MutableLiveData<Float> = MutableLiveData()

    var dollarValue: MutableLiveData<String> = MutableLiveData()
    var result: MutableLiveData<Float> = MutableLiveData()

    fun convertValue() {
        dollarValue.let {
            if (!it.value.equals("")) {
                result.value = it.value?.toFloat()?.times(rate)
            } else {
                result.value = 0f
            }
        }
    }




    /*fun setAmount(value: String) {
        this.dollarText = value
        //result = value.toFloat() * rate
        result.setValue(value.toFloat() * rate)
    }


    fun getResult(): MutableLiveData<Float> {
        return result
    }*/
}