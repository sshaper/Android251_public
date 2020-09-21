package com.example.androidsample_binding;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;

//Imported this
import com.example.androidsample_binding.databinding.ActivityMainBinding;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void convertCurrency(View view) {

        //EditText dollarText = findViewById(R.id.dollarText);
        //TextView textView = findViewById(R.id.textView);

        if (!binding.dollarText.getText().toString().equals("")) {

            float dollarValue = Float.parseFloat(binding.dollarText.getText().toString());
            float euroValue = dollarValue * 0.85F;
            binding.textView.setText(String.format(Locale.getDefault(), "%.2f", euroValue));
        } else {
            binding.textView.setText("test");
        }
    }
}