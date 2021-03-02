package com.ebookfrenzy.viewmodeldemo_modified.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import androidx.lifecycle.SavedStateHandle;

public class MainViewModel extends ViewModel {

    private static final String RESULT_KEY = "EuroValue";
    private static final Float usd_to_eu_rate = 0.74F;
    private String dollarText = "";
    private SavedStateHandle savedStateHandle;
    private MutableLiveData<Float> result;  // = new MutableLiveData<>();

    public MainViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
        result = savedStateHandle.getLiveData(RESULT_KEY);
    }

    public void setAmount(String value) {
        this.dollarText = value;
        Float convertedValue = Float.parseFloat(dollarText)*usd_to_eu_rate;
        result.setValue(convertedValue);

        savedStateHandle.set(RESULT_KEY, convertedValue);

        //result.setValue(Float.parseFloat(dollarText)*usd_to_eu_rate);
    }

    public MutableLiveData<Float> getResult()
    {
        return result;
    }
}