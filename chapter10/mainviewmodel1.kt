package com.example.book

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _count = mutableStateOf(0)
    var count: Int
        get() = _count.value
        private set(value) {
            _count.value = value
        }

    fun increment() {
        count = count + 1
    }
}
