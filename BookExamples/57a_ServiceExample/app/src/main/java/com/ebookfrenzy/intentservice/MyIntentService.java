package com.ebookfrenzy.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

//THIS CREATES AN INTENT SERVICE WHICH IS DESIGNED TO CREATE A SEPARATE THREAD AND HANDLES REQUESTS IN A ASYNCHRONOUS MANNER
public class MyIntentService extends IntentService {

    String TAG = "MyService";


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG,"Service Started on separate thread will run for 10 seconds");
        try {
            Thread.sleep(10000);
        }
        catch (Exception e) {

        }
        Log.i(TAG, "Service stopped");

    }

    public MyIntentService() {
        super("MyIntentService");
    }
}
