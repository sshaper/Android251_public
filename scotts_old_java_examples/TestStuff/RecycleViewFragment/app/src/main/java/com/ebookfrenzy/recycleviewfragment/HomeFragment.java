package com.ebookfrenzy.recycleviewfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeFragment extends Fragment{
    Data data = new Data();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //HAD TO CREATE THE VIEW VARIABLE HERE AND RETURN THE VARIABLE LATER SO I COULD USE VIEW WHEN I CALLED FINDVIEWBYID
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        List<String> list = data.getList();

        RecyclerView recyclerView = view.findViewById(R.id.my_recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyAdapter myAdapter = new MyAdapter(list);
        recyclerView.setAdapter(myAdapter);

        return view;


    }
}


