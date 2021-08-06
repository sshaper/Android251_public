package com.ebookfrenzy.startedservice1;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = "ServiceExample";

    @Override
    protected void onHandleIntent(Intent arg0) {
        Log.i(TAG, "Intent Service started");
        try {
            Thread.sleep(10000);
            Log.i(TAG,"Intent Service stopped");
        }
        catch (Exception e) {
            //return(e.getLocalizedMessage());
            Log.i(TAG,"Problem");
        }

    }
    public MyIntentService() {
        super("MyIntentService");
    }
}
