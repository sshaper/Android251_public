package com.ebookfrenzy.directreply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.content.Intent
import android.app.RemoteInput
import android.view.View
import android.app.PendingIntent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.graphics.drawable.Icon
import android.app.Notification
import com.google.android.material.R.color

import com.ebookfrenzy.directreply.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val notificationId = 101
    private val KEY_TEXT_REPLY = "key_text_reply"
    private lateinit var binding: ActivityMainBinding
    private val NOTIFICATION_REQUEST_CODE = 101
    private var notificationManager: NotificationManager? = null
    private val channelID = "com.ebookfrenzy.directreply.news"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestPermission(Manifest.permission.POST_NOTIFICATIONS,
            NOTIFICATION_REQUEST_CODE)

        notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelID,
            "DirectReply News", "Example News Channel")

        handleIntent()
    }

    private fun handleIntent() {

        val intent = this.intent

        val remoteInput = RemoteInput.getResultsFromIntent(intent)

        if (remoteInput != null) {

            val inputString = remoteInput.getCharSequence(
                KEY_TEXT_REPLY).toString()

            binding.textView.text = inputString

            val repliedNotification = Notification.Builder(this, channelID)
                .setSmallIcon(
                    android.R.drawable.ic_dialog_info)
                .setContentText("Reply received")
                .build()

            notificationManager?.notify(notificationId,
                repliedNotification)
        }
    }

    fun sendNotification(view: View) {

        val replyLabel = "Enter your reply here"
        val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
            .setLabel(replyLabel)
            .build()

        val resultIntent = Intent(this, MainActivity::class.java)

        val resultPendingIntent = PendingIntent.getActivity(
            this,
            0,
            resultIntent,
            PendingIntent.FLAG_MUTABLE
        )

        val icon = Icon.createWithResource(this@MainActivity,
            android.R.drawable.ic_dialog_info)

        val replyAction = Notification.Action.Builder(
            icon,
            "Reply", resultPendingIntent)
            .addRemoteInput(remoteInput)
            .build()

        val newMessageNotification = Notification.Builder(this, channelID)
            .setColor(ContextCompat.getColor(this,
                color.design_default_color_primary))
            .setSmallIcon(
                android.R.drawable.ic_dialog_info)
            .setContentTitle("My Notification")
            .setContentText("This is a test message")
            .addAction(replyAction).build()

        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(notificationId,
            newMessageNotification)
    }

    private fun createNotificationChannel(id: String,
                                          name: String, description: String) {

        val importance = NotificationManager.IMPORTANCE_HIGH
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