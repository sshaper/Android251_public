package com.ebookfrenzy.startedservice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(
                new Button.OnClickListener() {
                    int i;
                    String msg;
                    public void onClick(View v) {
                        i++;
                        msg = "Button Clicked " + i;
                        TextView textView = findViewById(R.id.textView);
                        textView.setText(msg);
                    }
                }
        );


    }


}