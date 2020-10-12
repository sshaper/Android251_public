package com.ebookfrenzy.viewmodeldemo.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private static final Float usd_to_eu_rate = 0.74F;
    public MutableLiveData<String> dollarValue = new MutableLiveData<>();
    //private String dollarText = "";

    //below was private in last example
    public MutableLiveData<Float> result = new MutableLiveData<>();

    /*
    public void setAmount(String value) {
    this.dollarText = value;
        result.setValue(Float.valueOf(dollarText)*usd_to_eu_rate);
    }
    public MutableLiveData<Float> getResult()
    {
        return result;
    }
    */

    public void convertValue() {
        if ((dollarValue.getValue() != null) &&
                (!dollarValue.getValue().equals(""))) {
            result.setValue(Float.parseFloat(dollarValue.getValue())
                    * usd_to_eu_rate);
        } else {
            result.setValue(0F);
        }
    }

}