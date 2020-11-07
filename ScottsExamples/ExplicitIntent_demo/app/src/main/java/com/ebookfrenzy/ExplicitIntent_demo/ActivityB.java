package com.ebookfrenzy.ExplicitIntent_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Bundle extras = getIntent().getExtras();
        Button btn = findViewById(R.id.returnFromBBtn);


        if(extras == null){
            return;
        }

        String questionString = extras.getString("questionString");
        final TextView textView = findViewById(R.id.textView1);
        textView.setText(questionString);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    public void finish(){
        Intent data = new Intent();
        EditText editText1 = findViewById(R.id.editText1);

        //SEND DATA BACK
        String returnString = editText1.getText().toString();
        data.putExtra("returnDataFromB", returnString);
        setResult(RESULT_OK, data);
        super.finish();

    }
}
