package com.ebookfrenzy.viewmodeltest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<CharSequence> text = new MutableLiveData<>();
    private MutableLiveData<CharSequence> FragAEditText = new MutableLiveData<>();
    private MutableLiveData<CharSequence> FragBEditText = new MutableLiveData<>();

    //THIS ARE THE GENERAL COMMANDS
    public void setText(CharSequence input) {
        text.setValue(input);
    }
    public LiveData<CharSequence> getText() {
        return text;
    }


    //THESE ARE SPECIFIC FOR THE EDIT TEXT ON BOTH FRAGA AND FRAGB
    public void FragAEditSetText(CharSequence input){FragAEditText.setValue(input);}
    public LiveData<CharSequence> FragAEditGetText(){return FragAEditText;}

    public void FragBEditSetText(CharSequence input){FragBEditText.setValue(input);}
    public LiveData<CharSequence> FragBEditGetText(){return FragBEditText;}
}
