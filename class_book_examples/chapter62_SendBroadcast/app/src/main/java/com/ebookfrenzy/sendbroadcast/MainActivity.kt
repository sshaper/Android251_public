package com.ebookfrenzy.sendbroadcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.content.IntentFilter
import android.content.BroadcastReceiver

class MainActivity : AppCompatActivity() {

    var receiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureReceiver()
    }

    private fun configureReceiver() {
        val filter = IntentFilter()
        filter.addAction("com.ebookfrenzy.sendbroadcast")
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED")
        //ACTIONS PAGE https://developer.android.com/reference/android/content/Intent#ACTION_VIEW
        receiver = MyReceiver()
        registerReceiver(receiver, filter)
    }

    fun broadcastIntent(view: View) {
        val intent = Intent()
        intent.action = "com.ebookfrenzy.sendbroadcast"
        intent.putExtra("name","Scott Shaper")
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}