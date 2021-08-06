package com.ebookfrenzy.asynctaskseparateclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static TextView myTextView;
    private TextView number;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = findViewById(R.id.myTextView);
        number = findViewById(R.id.number);

    }

    public void countNum(View v){
        num = num + 1;
        number.setText("The button has been clicked " + num + " times");
    }

    public void buttonClick(View view)
    {
        //I CREATE THIS ARRAY OF STRINGS THAT CONTAINS TWO NAMES
        String [] names = {"scott","karen","scottie"};

        //HERE I PASS IT TO THE DOING BACKGROUND METHOD
        AsyncTask task = new MyTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, names);

    }

    public static void addText(String text){
        myTextView.setText(text);
    }


}
