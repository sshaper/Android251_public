package com.ebookfrenzy.remotebound;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Messenger myService = null;
    boolean isBound;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(),
                RemoteService.class);

        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

        int i = 0;

    }

    private ServiceConnection myConnection =
            new ServiceConnection() {
                public void onServiceConnected(
                        ComponentName className,
                        IBinder service) {
                    myService = new Messenger(service);
                    isBound = true;
                }

                public void onServiceDisconnected(
                        ComponentName className) {
                    myService = null;
                    isBound = false;
                }
            };

    /*
    IN THIS SERVICE I CAN KEEP CLICKING THE BUTTON AND IT WILL WORK
    BECAUSE THE REMOTE SERVICE IS ON ANOTHER PROCESS
      */
    public void countClicks(View view){
        i++;
        TextView textView = findViewById(R.id.textView);
        textView.setText("The button was clicked " + i + " times.");
    }


    public void sendMessage(View view)
    {
        if (!isBound) return;

        Message msg = Message.obtain();

        Bundle bundle = new Bundle();
        bundle.putString("MyString", "Message Received");

        msg.setData(bundle);

        try {
            myService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}