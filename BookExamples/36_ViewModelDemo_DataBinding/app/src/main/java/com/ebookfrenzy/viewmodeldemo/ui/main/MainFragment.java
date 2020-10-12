package com.ebookfrenzy.viewmodeldemo.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import com.ebookfrenzy.viewmodeldemo.databinding.MainFragmentBinding;
import static com.ebookfrenzy.viewmodeldemo.BR.myViewModel;

import com.ebookfrenzy.viewmodeldemo.R;

import java.util.Locale;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    //private EditText dollarText;
    //private TextView resultText;
    //private Button convertButton;



    public MainFragmentBinding binding;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.main_fragment, container, false);

        binding.setLifecycleOwner(this);

        return binding.getRoot();

        //return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        /*
        dollarText = getView().findViewById(R.id.dollarText);
        resultText = getView().findViewById(R.id.resultText);
        convertButton = getView().findViewById(R.id.convertButton);
        final Observer<Float> resultObserver = new Observer<Float>() {
            @Override
            public void onChanged(@Nullable final Float result) {
                resultText.setText(String.format(Locale.getDefault(),"%.2f",
                        result));
            }
        };
        mViewModel.getResult().observe(getViewLifecycleOwner(), resultObserver);
        convertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (!dollarText.getText().toString().equals("")) {
                    mViewModel.setAmount(dollarText.getText().toString());
                } else {
                    resultText.setText("No Value");
                }
            }
        });*/


        binding.setVariable(myViewModel, mViewModel);
    }

}