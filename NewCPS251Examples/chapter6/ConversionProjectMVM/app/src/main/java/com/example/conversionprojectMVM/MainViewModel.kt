package com.example.conversionprojectMVM

import androidx.lifecycle.ViewModel
import java.text.DecimalFormat

class MainViewModel : ViewModel() {
    private var formattedCurrency: String = ""

    fun convertCurrency(usdAmount: Double) {
        val euroAmount = usdAmount * 0.93 // Example conversion rate
        val formatter = DecimalFormat("#,###.00")
        formattedCurrency = "€${formatter.format(euroAmount)}"
    }

    fun getFormattedCurrency(): String {
        return formattedCurrency
    }
}