package com.example.conversionprojectLiveData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class MainViewModel : ViewModel() {
    // LiveData to hold the formatted currency
    private val _formattedCurrency = MutableLiveData<String>()
    val formattedCurrency: LiveData<String> = _formattedCurrency

    fun convertCurrency(usdAmount: Double) {
        val euroAmount = usdAmount * 0.93 // Example conversion rate
        val formatter = DecimalFormat("#,###.00")
        _formattedCurrency.value = "€${formatter.format(euroAmount)}"
    }
}