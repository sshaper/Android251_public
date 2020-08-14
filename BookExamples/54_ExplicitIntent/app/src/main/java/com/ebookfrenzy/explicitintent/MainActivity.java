package com.ebookfrenzy.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int request_code = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void askQuestion(View view) {

        Intent i = new Intent(this, ActivityB.class);

        final EditText editText1 = findViewById(R.id.editText1);
        String myString = editText1.getText().toString();
        i.putExtra("qString", myString);
        startActivityForResult(i, request_code);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == request_code) &&
                (resultCode == RESULT_OK)) {

            TextView textView1 = findViewById(R.id.textView1);

            String returnString =
                    data.getExtras().getString("returnData");

            textView1.setText(returnString);
        }
    }
}