package com.ebookfrenzy.stopwatchappv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
