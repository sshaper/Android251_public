package com.ebookfrenzy.viewmodeltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_a, new FragmentA())
                .add(R.id.container_b, new FragmentB())
                .commit();
    }
}
