package com.ebookfrenzy.ExplicitIntent_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        Button btn = findViewById(R.id.sendBackBtn);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return;
        }

        String test = extras.getString("test");
        final TextView textView = findViewById(R.id.activityCTextView);
        textView.setText(test);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void finish() {
        Intent data = new Intent();
        String str = "This is the string returned from C";
        data.putExtra("returnDataFromC", str);
        setResult(RESULT_OK, data);
        super.finish();

    }
}


