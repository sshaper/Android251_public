package com.ebookfrenzy.jetpackdatabindingv2.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> outputValue = new MutableLiveData<>();

    public void onTextChanged(CharSequence s, int start, int before, int count) {
           outputValue.setValue(String.valueOf(s));
    }

}