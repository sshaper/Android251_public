package com.example.conversionprojectDataBinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // LiveData to hold the formatted currency
    var _formattedCurrency = MutableLiveData<Double>()
    var _dollarValue = MutableLiveData<String>()


    fun convertCurrency() {
        _dollarValue.let {
            if (!it.value.equals("")) {
                _formattedCurrency.value = it.value?.toDouble()?.times(0.93)
            }
        }
    }
}
