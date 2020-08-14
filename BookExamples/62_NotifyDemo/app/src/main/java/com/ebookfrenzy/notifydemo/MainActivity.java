package com.ebookfrenzy.notifydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.Color;
import android.app.Notification;
import android.view.View;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.Icon;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

        createNotificationChannel(
                "com.ebookfrenzy.notifydemo.news",
                "NotifyDemo News",
                "Example News Channel");
    }

    public void sendNotification(View view) {

        int notificationID = 101;

        Intent resultIntent = new Intent(this, ResultActivity.class);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        String channelID = "com.ebookfrenzy.notifydemo.news";

        final Icon icon = Icon.createWithResource(MainActivity.this,
                android.R.drawable.ic_dialog_info);

        Notification.Action action =
                new Notification.Action.Builder(icon, "Open", pendingIntent)
                        .build();

        Notification notification =
                new Notification.Builder(MainActivity.this,
                        channelID)
                        .setContentTitle("Example Notification")
                        .setContentText("This is an  example notification.")
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setChannelId(channelID)
                        .setContentIntent(pendingIntent)
                        .setActions(action)
                        .build();

        notificationManager.notify(notificationID, notification);
    }

        protected void createNotificationChannel(String id, String name,
                                             String description) {

        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel channel =
                new NotificationChannel(id, name, importance);

        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(
                new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        notificationManager.createNotificationChannel(channel);
    }

}