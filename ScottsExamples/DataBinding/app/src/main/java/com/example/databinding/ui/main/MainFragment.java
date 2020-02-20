package com.example.databinding.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.databinding.R;
import com.example.databinding.databinding.MainFragmentBinding;

import static com.example.databinding.BR.myViewModel;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    public MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.main_fragment, container, false);
        //MainFragmentBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setVariable(myViewModel, this);
        // TODO: Use the ViewModel
    }

}
