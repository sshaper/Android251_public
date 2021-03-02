package com.ebookfrenzy.navigationdemoV2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.net.Uri;
import android.widget.EditText;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        TextView argText = getView().findViewById(R.id.argText);
        TextView number = getView().findViewById(R.id.argNumber);
        SecondFragmentArgs args = SecondFragmentArgs.fromBundle(getArguments());
        String message = args.getMessage();
        argText.setText(message);

        //THIS IS THE NUMBER I ADDED
        int num = args.getNumber();

        //I HAD TO CONVERT TO A STRING TO PUT IT INTO A TEXTVIEW
        number.setText(String.valueOf(num));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}