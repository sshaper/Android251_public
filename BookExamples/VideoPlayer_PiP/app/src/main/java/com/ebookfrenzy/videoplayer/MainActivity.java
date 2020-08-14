package com.ebookfrenzy.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;
import android.net.Uri;
import android.widget.MediaController;
import android.util.Log;
import android.media.MediaPlayer;
import android.view.View;
import android.app.PictureInPictureParams;
import android.util.Rational;
import android.widget.Button;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.graphics.drawable.Icon;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 101;
    private BroadcastReceiver receiver;
    private VideoView videoView;
    private MediaController mediaController;
    String TAG = "VideoPlayer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureVideoView();
    }

    public void enterPipMode(View view) {

        Button pipButton = findViewById(R.id.pipButton);

        Rational rational = new Rational(videoView.getWidth(),
                videoView.getHeight());

        PictureInPictureParams params =
                new PictureInPictureParams.Builder()
                        .setAspectRatio(rational)
                        .build();

        pipButton.setVisibility(View.INVISIBLE);
        videoView.setMediaController(null);
        enterPictureInPictureMode(params);
    }

    private void configureVideoView() {

        videoView =
                findViewById(R.id.videoView1);

        videoView.setVideoURI(Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.movie));

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()  {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                        Log.i(TAG, "Duration = " +
                                videoView.getDuration());
                    }
                });

        videoView.start();
    }

    private void createPipAction() {

        final ArrayList<RemoteAction> actions = new ArrayList<>();

        Intent actionIntent =
                new Intent("com.ebookfrenzy.videoplayer.VIDEO_INFO");

        final PendingIntent pendingIntent =
                PendingIntent.getBroadcast(MainActivity.this,
                        REQUEST_CODE, actionIntent, 0);

        final Icon icon =
                Icon.createWithResource(MainActivity.this,
                        R.drawable.ic_info_24dp);
        RemoteAction remoteAction = new RemoteAction(icon, "Info",
                "Video Info", pendingIntent);

        actions.add(remoteAction);

        PictureInPictureParams params =
                new PictureInPictureParams.Builder()
                        .setActions(actions)
                        .build();

        setPictureInPictureParams(params);

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        Button pipButton = findViewById(R.id.pipButton);

        if (isInPictureInPictureMode) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(
                    "com.ebookfrenzy.videoplayer.VIDEO_INFO");

            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context,
                                      Intent intent) {
                    Toast.makeText(context,
                            "Favorite Home Movie Clips",
                            Toast.LENGTH_LONG).show();
                }
            };

            registerReceiver(receiver, filter);
            createPipAction();

        } else {
            pipButton.setVisibility(View.VISIBLE);
            videoView.setMediaController(mediaController);

            if (receiver != null) {
                unregisterReceiver(receiver);
            }
        }
    }

}