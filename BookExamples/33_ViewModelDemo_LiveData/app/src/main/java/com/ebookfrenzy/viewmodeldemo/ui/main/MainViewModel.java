package com.ebookfrenzy.viewmodeldemo.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private static final Float usd_to_eu_rate = 0.74F;
    private String dollarText = "";

    /*LINE NUMBER 13 WAS CHANGED FROM
    private Float result = 0F;
     */
    private MutableLiveData<Float> result = new MutableLiveData<>();


    public void setAmount(String value) {
        this.dollarText = value;
        /*
        LINE 23 WAS CHANGED FROM THE CODE BELOW BECAUSE WE HAD TO USE THE setValue() METHOD OF
        THE MUTABLELIVEDATA INSTANCE
        result = Float.valueOf(dollarText) * usd_to_eu_rate;
        */
        result.setValue(Float.valueOf(dollarText)*usd_to_eu_rate);
    }

    /*
    LINE 30 WAS CHANGED FROM THE CODE BELOW BECAUSE IT HAD TO BE PART OF THE MUTABLELIVEDATA
    INSTANCE
    public Float getResult()
     */
    public MutableLiveData<Float> getResult()
    {
        return result;
    }

}
