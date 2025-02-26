package com.example.livedataautoupdate

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel class that manages the updating of names from an array every second.
 */
class MainViewModel : ViewModel() {

    // Array of names that will be displayed sequentially
    private val names = arrayOf(
        "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Hank", "Ivy", "Jack",
        "Kara", "Liam", "Mia", "Noah", "Olivia", "Paul", "Quinn", "Ryan", "Sophia", "Tom",
        "Uma", "Victor", "Wendy", "Xavier", "Yara", "Zane"
    )

    // Index to track the current name being displayed
    private var currentIndex = 0

    // LiveData object to hold the current name and allow observers to react to updates
    private val _currentName = MutableLiveData<String>()
    val currentName: LiveData<String> = _currentName

    // Timer that updates the name every second
    private val timer: CountDownTimer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            // Update the LiveData with the next name in the array
            _currentName.value = names[currentIndex]

            // Move to the next index, looping back to 0 if at the end of the list
            currentIndex = (currentIndex + 1) % names.size
        }

        override fun onFinish() {
            start() // Restart the timer if it somehow finishes (though it shouldn't)
        }
    }

    // Initialize the timer when the ViewModel is created
    init {
        timer.start()
    }

    // Cancel the timer when the ViewModel is cleared to prevent memory leaks
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
