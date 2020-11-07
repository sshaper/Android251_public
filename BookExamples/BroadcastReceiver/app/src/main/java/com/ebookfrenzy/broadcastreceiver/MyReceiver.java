package com.ebookfrenzy.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        String message = "Broadcast intent detected " + intent.getAction();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}