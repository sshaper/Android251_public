package com.ebookfrenzy.recycleviewtwofragmentcardview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    public static String KEY_COUNTRY_POSITION="KEY_COUNTRY_POSITION";



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        return view;
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();

        if(bundle != null && bundle.containsKey(KEY_COUNTRY_POSITION)){
            showSelectedCountry(bundle.getInt(KEY_COUNTRY_POSITION));
        }
    }

    public void showSelectedCountry(Integer countryPosition){
        Data data = new Data();
        TextView countryTxt = getView().findViewById(R.id.detail_textview);
        List<String> countries = data.getList();
        String country = countries.get(countryPosition);
        countryTxt.setText(country);
    }
}
