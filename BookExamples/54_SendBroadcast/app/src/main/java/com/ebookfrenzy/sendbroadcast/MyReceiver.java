package com.ebookfrenzy.sendbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentData="";
        Bundle extras = intent.getExtras();

        //THIS PREVENTS THE STRING TO BE ADDED IF POWER DISCONNECT IS SENT
        if(extras.getString("MyData") != null){
            intentData = extras.getString("MyData");
        }
        else{
            intentData = "";
        }

        String message = "Broadcast intent detected " + intent.getAction() + intentData;

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
