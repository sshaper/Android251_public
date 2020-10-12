package com.ebookfrenzy.jetpackdatabindingv2.ui.main;

//import androidx.lifecycle.ViewModelProviders;
//THE ABOVE IS DEPRECATED AND REPLACED BY THE ONE BELOW
import androidx.lifecycle.ViewModelProvider;

//LOADED WITH FRAGMENT PLUS VIEW MODEL
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//IMPORTS ADDED HAD TO ADD THE DATABINDING TO GRADLE FILE.
import androidx.databinding.DataBindingUtil;
import com.ebookfrenzy.jetpackdatabindingv2.databinding.MainFragmentBinding;
import static com.ebookfrenzy.jetpackdatabindingv2.BR.myViewModel;

//THIS WAS ADDED BY FRAGMENT PLUS VIEW MODEL
import com.ebookfrenzy.jetpackdatabindingv2.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    public MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.main_fragment, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setVariable(myViewModel, mViewModel);
   }

}