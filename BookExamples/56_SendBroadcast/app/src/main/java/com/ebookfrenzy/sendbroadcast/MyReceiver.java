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
        String message = "Broadcast intent detected " + intent.getAction() + intent.getExtras().getString("MyData");

        Toast.makeText(context, message,
                Toast.LENGTH_LONG).show();
    }
}
