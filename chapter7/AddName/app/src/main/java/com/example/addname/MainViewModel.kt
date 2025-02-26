package com.example.addname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var _inputName = MutableLiveData<String>()
    var _name = MutableLiveData<String>()
    var name: LiveData<String> = _name

    fun addName() {
        _inputName.let {
            if(!it.value.isNullOrEmpty()){
                _name.value = it.value
                _inputName.value = "" // Clear the input field
            }

        }
    }
}
