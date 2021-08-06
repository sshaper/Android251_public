package com.example.weatherapp;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private String json;

    public String getJson(){
        return json;
    }

    public void setJson(String json){
        this.json = json;
    }



}
