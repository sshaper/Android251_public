package com.ebookfrenzy.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Integer value = 0;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.displayValue);
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    public void addValue(View v){
        value++;
        textView.setText("The value is " + value);
    }
}
