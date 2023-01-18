package com.ebookfrenzy.remotebound

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Handler
import android.os.Message
import android.os.Messenger
import android.widget.Toast
import android.os.*

class RemoteService : Service() {

    inner class IncomingHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {

            val data = msg.data
            val dataString = data.getString("MyString")
            Toast.makeText(applicationContext,
                dataString, Toast.LENGTH_SHORT).show()
        }
    }

    private val myMessenger = Messenger(IncomingHandler())

    override fun onBind(intent: Intent): IBinder {
        return myMessenger.binder
    }
}