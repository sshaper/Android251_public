package com.ebookfrenzy.serviceexample

import android.content.Intent
import androidx.core.app.JobIntentService

import android.util.Log

class MyJobIntentService : JobIntentService() {

    private val TAG = "ServiceExample"

    override fun onHandleWork(intent: Intent) {
        Log.i(TAG, "Job Service started")

        var i: Int = 0

        while (i <= 3) {
            try {
                Thread.sleep(10000)
                i++
            } catch (e: Exception) {
            }
            Log.i(TAG, "Service running")
        }
    }
}