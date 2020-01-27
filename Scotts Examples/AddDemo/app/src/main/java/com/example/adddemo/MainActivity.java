package com.example.adddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView answer;
    EditText num1;
    EditText num2;
    Button addNumbersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        answer = findViewById(R.id.answer);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
    }

    public void addNumbers(View view){
       Integer firstNumber = Integer.parseInt(num1.getText().toString());
       Integer secondNumber = Integer.parseInt(num2.getText().toString());
       Integer ans = firstNumber + secondNumber;
       answer.setText(ans.toString());
    }
}
