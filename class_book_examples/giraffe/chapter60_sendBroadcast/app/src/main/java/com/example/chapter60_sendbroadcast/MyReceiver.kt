package com.example.chapter60_sendbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract.Constants
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val myName: String? = intent.getStringExtra("myName")
        var message: String = "Broadcast " + intent.action +" no name sent"

        //NOT IN BOOK BUT THIS IS HOW YOU WOULD RECEIVE DATA USING A BROADCAST RECEIVER
        myName?.let{
            message = "Broadcast intent detected " + intent.action + " " + myName
        }

        Toast.makeText(context, message,Toast.LENGTH_LONG).show()
    }
}