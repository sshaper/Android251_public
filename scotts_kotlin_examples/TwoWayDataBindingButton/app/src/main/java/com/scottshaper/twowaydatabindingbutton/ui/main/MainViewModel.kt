package com.scottshaper.twowaydatabindingbutton.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var inputText: MutableLiveData<String> = MutableLiveData()
    var outputText: MutableLiveData<String> = MutableLiveData()

    fun changeText(){
        outputText.value = inputText.value
    }
}