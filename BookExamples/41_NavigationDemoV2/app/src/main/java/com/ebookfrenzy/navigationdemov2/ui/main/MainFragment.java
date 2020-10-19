package com.ebookfrenzy.navigationdemoV2.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebookfrenzy.navigationdemoV2.R;
import android.widget.Button;
import androidx.navigation.Navigation;
import android.widget.EditText;

public class MainFragment extends Fragment {

    //MAINVIEW MODEL IS NOT BEING USED IN THIS PROJECT THIS WAS SET UP BY DEFAULT
    private MainViewModel mViewModel;

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
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //deprecated but still added by default.
        //mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Button button = getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userText = getView().findViewById(R.id.userText);
                MainFragmentDirections.MainToSecond action = MainFragmentDirections.mainToSecond();

                //THE METHOD setMessage EXISTS BECAUSE WE HAVE AN ARGUMENT WITH THE NAME OF MESSAGE IN OUR NAVIGATION_GRAPH.XML FILE.  YOU MAY HAVE TO CLICK BUILD->MAKE PROJECT BECAUSE IT MAY NOT EXIST WHEN FIRST ADDED TO THE XML
                action.setMessage(userText.getText().toString());
                Navigation.findNavController(view).navigate(action);


            }
        });

    }

}