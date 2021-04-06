package com.ebookfrenzy.localbound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.IBinder;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.ServiceConnection;
import com.ebookfrenzy.localbound.BoundService.MyLocalBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BoundService myService;
    boolean isBound = false;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BoundService.class);

        //BIND_AUTO_CREATE  automatically create the service as long as the binding exists
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public void showMsg(View view){
        TextView btnClicks = findViewById(R.id.btnClicks);
        i++;

        btnClicks.setText("Button has been clicked "  + i + " times.");
    }

    public void showTime(View view)
    {
        String currentTime = myService.getCurrentTime();
        TextView myTextView = findViewById(R.id.myTextView);

        myTextView.setText(currentTime);
    }


    private ServiceConnection myConnection = new ServiceConnection() {
        @Override

        //The onServiceConnected() method will be called when the client binds successfully to the service.
        public void onServiceConnected(ComponentName className, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}