package com.ebookfrenzy.viewmodeldemo.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private static final Float usd_to_eu_rate = 0.74F;
    private String dollarText = "";

    //private Float result = 0F;
    private MutableLiveData<Float> result = new MutableLiveData<>();


    public void setAmount(String value) {
        this.dollarText = value;
        //result = Float.parseFloat(dollarText)*usd_to_eu_rate;
        result.setValue(Float.parseFloat(dollarText)*usd_to_eu_rate);

    }

    //public Float getResult()
    public MutableLiveData<Float> getResult(){
        return result;
    }

}