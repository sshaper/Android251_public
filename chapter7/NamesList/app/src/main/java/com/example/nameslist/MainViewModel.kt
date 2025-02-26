package com.example.nameslist

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // List of names to cycle through
    private val names = listOf("John Doe", "Jane Smith", "Alice Johnson", "Bob Brown", "Charlie Davis")

    // LiveData holding the current name (now a String instead of MyUserModel)
    private val _name = MutableLiveData(names[0])
    val name: LiveData<String> = _name

    // Handler to update name every second
    private val handler = Handler(Looper.getMainLooper())
    private var index = 0

    // Runnable to update the name every second
    private val updateNameRunnable = object : Runnable {
        override fun run() {
            index = (index + 1) % names.size  // Cycle through names
            _name.value = names[index] // Update LiveData
            handler.postDelayed(this, 1000) // Repeat every 1 second
        }
    }

    init {
        handler.postDelayed(updateNameRunnable, 1000) // Start updating names
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacks(updateNameRunnable) // Stop updates when ViewModel is cleared
    }
}
