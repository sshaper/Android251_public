package com.ebookfrenzy.ExplicitIntent_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int request_code_1 = 1;
    private static final int request_code_2 = 2;
    TextView editText1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        editText1 = findViewById(R.id.editText1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToActB();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToActC();
            }
        });
    }

    private void sendToActB() {
        Intent i = new Intent(this,ActivityB.class);
        String myString = editText1.getText().toString();
        i.putExtra("questionString",myString);
        startActivityForResult(i, request_code_1);
    }

    private void sendToActC() {
        Intent i = new Intent(this,ActivityC.class);
        i.putExtra("test","This is a test");
        startActivityForResult(i, request_code_2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        TextView textView1 = findViewById(R.id.textView1);
        if((requestCode == request_code_1) && (resultCode == RESULT_OK)){
            String returnString = data.getExtras().getString("returnDataFromB");
            textView1.setText(returnString);
        }
        else if((requestCode == request_code_2) && (resultCode == RESULT_OK)){
            String returnString = data.getExtras().getString("returnDataFromC");
            textView1.setText(returnString);
        }
    }
}
