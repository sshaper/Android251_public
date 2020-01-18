package com.ebookfrenzy.viewmodeldemo.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ebookfrenzy.viewmodeldemo.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private EditText dollarText;
    private TextView resultText;
    private Button convertButton;

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
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        dollarText = getView().findViewById(R.id.dollarText);
        resultText = getView().findViewById(R.id.resultText);
        convertButton = getView().findViewById(R.id.convertButton);

        resultText.setText(mViewModel.getResult().toString());

        convertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (!dollarText.getText().toString().equals("")) {
                    mViewModel.setAmount(dollarText.getText().toString());
                    resultText.setText(mViewModel.getResult().toString());
                } else {
                    resultText.setText("No Value");
                }
            }
        });


    }

}
