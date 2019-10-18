package com.ebookfrenzy.viewmodeltest;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentB extends Fragment {
    SharedViewModel viewModel;
    EditText fragBEdit;
    TextView fragB;
    Button fragBBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        fragBEdit = v.findViewById(R.id.fragBEdit);
        fragBBtn = v.findViewById(R.id.fragBBtn);
        fragB = v.findViewById(R.id.fragBView);


        fragBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setText(fragBEdit.getText());
                viewModel.FragBEditSetText(fragBEdit.getText());
             }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);

        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                fragB.setText(charSequence);
            }
        });


        viewModel.FragBEditGetText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(@Nullable CharSequence charSequence) {
                fragBEdit.setText(charSequence);
            }
        });
    }
}