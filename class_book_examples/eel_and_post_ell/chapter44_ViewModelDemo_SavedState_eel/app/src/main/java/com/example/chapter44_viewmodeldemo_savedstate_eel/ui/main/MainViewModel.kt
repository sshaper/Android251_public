package com.example.chapter44_viewmodeldemo_savedstate_eel.ui.main
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle

const val RESULT_KEY = "Euro Value"

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val rate = 0.74f
    private var dollarText = ""
    //THIS WILL RESTORE WHAT THE RESULT IS (BASED UPON THE SAVE) AFTER A TERMINATION
    private var result: MutableLiveData<Float> = savedStateHandle.getLiveData(RESULT_KEY)

    //COMMENT THIS OUT AND UNCOMMENT LINE ABOVE TO SEE CODE SAVE AFTER TERMINATION
    //private var result: MutableLiveData<Float> = MutableLiveData()

    fun setAmount(value: String) {
        this.dollarText = value
        val convertedValue = value.toFloat() * rate
        result.value = convertedValue
        //THIS SAVES THE VALUE INTO THE SAVEDSTATEHANDLE
        savedStateHandle.set(RESULT_KEY, convertedValue)
    }

    fun getResult(): MutableLiveData<Float> {
        return result
    }
}