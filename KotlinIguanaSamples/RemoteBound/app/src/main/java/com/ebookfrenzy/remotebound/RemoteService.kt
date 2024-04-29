package com.ebookfrenzy.remotebound

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log

class RemoteService : Service() {

    class IncomingHandler : Handler(Looper.getMainLooper()) {

        val TAG = "RemoteServer"

        override fun handleMessage(msg: Message) {
            val data = msg.data
            val dataString = data.getString("MyString")
            Log.i(TAG, "Message = $dataString")
        }
    }

    private val myMessenger = Messenger(IncomingHandler())

    override fun onBind(intent: Intent): IBinder {
        return myMessenger.binder
    }
}