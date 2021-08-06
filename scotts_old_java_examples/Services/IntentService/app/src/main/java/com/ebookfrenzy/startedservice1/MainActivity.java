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
       
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                startService(intent);
            }
        });


        btn2.setOnClickListener(
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