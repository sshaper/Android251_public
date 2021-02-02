package com.ebookfrenzy.statechange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "StateChange";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");

        final EditText text1 = findViewById(R.id.text1);
        final EditText text2 = findViewById(R.id.text2);
        CharSequence txt1 = text1.getText();
        CharSequence txt2 = "sam";
        outState.putCharSequence("txt1", txt1);
        outState.putCharSequence("txt2", txt2);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");

        final EditText text1 = findViewById(R.id.text1);
        final EditText text2 = findViewById(R.id.text2);
        CharSequence txt1 = savedInstanceState.getCharSequence("txt1");
        text1.setText(txt1);
        CharSequence txt2 = savedInstanceState.getCharSequence("txt2");
        text2.setText(txt2);


    }

}