package com.ebookfrenzy.sendbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //SLIGHT MODIFICATION FROM THE BOOK
        //I PASS TWO DIFFERENT INTENTS AND DEPENDING ON WHAT GETS PASSED THE CORRECT MESSAGE IS DISPLAYED.
        String msg;

        if(intent.getAction()=="com.ebookfrenzy.sendbroadcast1"){
            msg = "Broadcast intent detected 1" + intent.getAction() + intent.getExtras().getString("MyData1");
        }
        else {
            msg = "Broadcast intent detected 2" + intent.getAction() + intent.getExtras().getString("MyData2");
        }

        Toast.makeText(context, msg,
                Toast.LENGTH_LONG).show();
    }
}
