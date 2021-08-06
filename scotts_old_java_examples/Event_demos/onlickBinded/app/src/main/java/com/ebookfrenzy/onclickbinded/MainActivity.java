package com.ebookfrenzy.onclickbinded;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ebookfrenzy.onclickbinded.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); NOT NEEDED BECAUSE WE ARE USING ACTIVITYMAINBINDING.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){

                binding.textView.setText("It Works!");
            }
        }

        );
    }
}