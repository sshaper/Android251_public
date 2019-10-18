package com.ebookfrenzy.viewmodeltest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<CharSequence> text = new MutableLiveData<>();

    public void setText(CharSequence input) {
        text.setValue(input);
    }
    public LiveData<CharSequence> getText() {
        return text;
    }




    private MutableLiveData<CharSequence> FragAEditText = new MutableLiveData<>();
    public void FragAEditSetText(CharSequence input){FragAEditText.setValue(input);}
    public LiveData<CharSequence> FragAEditGetText(){return FragAEditText;}



    private MutableLiveData<CharSequence> FragBEditText = new MutableLiveData<>();
    public void FragBEditSetText(CharSequence input){FragBEditText.setValue(input);}
    public LiveData<CharSequence> FragBEditGetText(){return FragBEditText;}
}
