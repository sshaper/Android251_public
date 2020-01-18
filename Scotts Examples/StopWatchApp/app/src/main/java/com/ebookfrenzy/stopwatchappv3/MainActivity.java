package com.ebookfrenzy.stopwatchappv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    //LOGCAT TO TEST
    private static final String TAG = "MyActivity";

    //RECORDS WHETHER THE APP WAS RUNNING BEFORE THE ONSTOP() METHOD WAS CALLED
    private boolean wasRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        IF THE SAVED INSTANCE STATE DOES NOT EQUAL WE RESTORE THE VALUES FROM THE BUNDLE
         */
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        /*
        WE WANT THE RUN TIMER METHOD TO START RUNNING WHEN THE ACTIVITY GETS CREATED.
         */
        runTimer();
    }

    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeView = findViewById(R.id.time_view);
        /*
           A HANDLER IS A ANDROID CLASS YOU CAN USE TO SCHEDULE CODE THAT SHOULD BE RUN AND SOME POINT IN THE FUTURE. YOU CAN ALSO USE IT TO POST CODE THAT NEEDS TO RUN ON A DIFFERENT THREAD OTHER THAN THE MAIN ANDROID THREAD. HERE WE ARE GOING TO USE IT TO SCHEDULE THE STOPWATCH CODE TO RUN EVERY SECOND.
        */
        final Handler handler = new Handler();
        /*
        THE POST METHOD PASSING IN A NEW RUNNABLE THE POST METHOD PROCESSES CODE WITHOUT DELAY, SO THE CODE IN THE RUNNABLE WILL RUN ALMOST IMMEDIATELY
         */
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                /*
                POST THE CODE IN THE RUNNABLE TO BE RUN AGAIN AFTER A DELAY OF 1000 MILLISECONDS.
                 */
                handler.postDelayed(this,1000);
            }
        });
    }
    /*
    WHEN onSaveInstanceState IS CALLED WE SAVE THE VALUES OF THE VARIABLES INTO THE BUNDLE.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop(){
        super.onStop();
        wasRunning = running;
        running = false;
        Log.i(TAG,"onStop");


    }

    @Override
    protected void onStart(){
        super.onStart();
        if(wasRunning){
            running = true;
            Log.i(TAG,"was running true");
        }
        Log.i(TAG, "onStart");
    }
}
