package com.ebookfrenzy.recycleviewfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> nameList;

    public MyAdapter(List<String> list){
        nameList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        /* I HAD TO RUN FILE/INVALIDATE CACHES RESTART TO GET IT TO RECONIZE R.LAYOUT.ITEMS */
        View view = inflater.inflate(R.layout.items, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position){
        final String name = nameList.get(position);
        holder.textView.setText(name);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textViewScott);
        }
    }

    @Override
    public int getItemCount(){
        return nameList.size();
    }
}
