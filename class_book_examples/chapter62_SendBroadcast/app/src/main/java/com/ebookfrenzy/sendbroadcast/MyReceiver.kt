package com.ebookfrenzy.sendbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //I added the following two lines
        var extras = intent.extras ?: return
        var ext = extras.getString("scott")

        val message = "Broadcast intent detected " + intent.action + ext

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}