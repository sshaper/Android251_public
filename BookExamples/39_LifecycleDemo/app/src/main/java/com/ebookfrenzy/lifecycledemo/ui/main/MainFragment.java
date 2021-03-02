package com.ebookfrenzy.lifecycledemo.ui.main;

//older class
//import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebookfrenzy.lifecycledemo.R;
import com.ebookfrenzy.lifecycledemo.DemoObserver;
import com.ebookfrenzy.lifecycledemo.DemoOwner;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private DemoOwner demoOwner;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //book had this
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        //replaced with this
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // TODO: Use the ViewModel
        //demoOwner = new DemoOwner();
        //demoOwner.startOwner();
        //demoOwner.stopOwner();
        getLifecycle().addObserver(new DemoObserver());
    }

}