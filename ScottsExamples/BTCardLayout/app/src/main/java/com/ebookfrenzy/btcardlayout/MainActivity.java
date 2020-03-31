package com.ebookfrenzy.btcardlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addName (View v){

        EditText n;
        String name;

        n = findViewById(R.id.nameEditText);
        name = n.getText().toString();
        Log.i(TAG, name);
    }
}
