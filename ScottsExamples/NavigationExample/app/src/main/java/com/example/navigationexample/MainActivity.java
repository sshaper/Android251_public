package com.example.navigationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.navigationexample.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity implements SecondFragment.
        OnFragmentInteractionListener{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment);

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
