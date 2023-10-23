package com.ebookfrenzy.notifydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import android.app.Notification
import android.view.View
import android.app.PendingIntent
import android.content.Intent
import android.graphics.drawable.Icon
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var notificationManager: NotificationManager? = null
    private val NOTIFICATION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission(Manifest.permission.POST_NOTIFICATIONS,
            NOTIFICATION_REQUEST_CODE)

        notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "com.ebookfrenzy.notifydemo.news",
            "NotifyDemo News",
            "Example News Channel")
    }

    fun sendNotification(view: View) {

        val notificationID = 101
        val channelID = "com.ebookfrenzy.notifydemo.news"
        val resultIntent = Intent(this, ResultActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            resultIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val icon: Icon = Icon.createWithResource(this, android.R.drawable.ic_dialog_info)

        val action: Notification.Action =
            Notification.Action.Builder(icon, "Open", pendingIntent).build()

        val notification = Notification.Builder(this@MainActivity,
            channelID)
            .setContentTitle("Example Notification")
            .setContentText("This is an example notification.")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setChannelId(channelID)
            .setContentIntent(pendingIntent)
            .setActions(action)
            .build()

        notificationManager?.notify(notificationID, notification)
    }

    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        notificationManager?.createNotificationChannel(channel)
    }

    private fun requestPermission(permissionType: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this,
            permissionType)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(permissionType), requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            NOTIFICATION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0]
                    != PackageManager.PERMISSION_GRANTED
                ) {

                    Toast.makeText(
                        this,
                        "Notification permission required",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}