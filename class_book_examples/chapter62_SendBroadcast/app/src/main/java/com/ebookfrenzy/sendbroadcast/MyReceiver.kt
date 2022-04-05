package com.ebookfrenzy.sendbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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
        val message = "Broadcast intent detected " + intent.action + "  " + name
        Toast.makeText(context, message,Toast.LENGTH_LONG).show()
    }
}