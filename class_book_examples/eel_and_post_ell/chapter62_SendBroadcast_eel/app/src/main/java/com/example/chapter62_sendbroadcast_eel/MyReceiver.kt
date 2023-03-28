package com.example.chapter62_sendbroadcast_eel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras?:return
        var name = ""
        extras.getString("name").let {
            if (it != null) {
                name = it
            }
        }

        var message = "Broadcast intent detected " + intent.action + "  " + name

        //I DID THIS TO DETERMINE WHICH MESSAGE TO FIRE DEPENDING ON THE ACTION
        //BASICALLY I JUST CHANGE THE MESSAGE.
        if(intent.action == "android.intent.action.ACTION_POWER_DISCONNECTED"){
            message = "Power has been disconnected"
        }
        Toast.makeText(context, message,Toast.LENGTH_LONG).show()
    }
}