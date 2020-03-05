package com.ebookfrenzy.smallrecycleviewdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> nameList;
    final static String TAG = "MyActivity";

    public MyAdapter(List<String> list){
        nameList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    /*
    ONBINDVIEWHOLDER IS CALLED FOR EACH ITEM AS ITS DISPLAYED, SO IF YOU HAVE THE ITEM.XML LINEAR LAYOUT HEIGHT SET TO MATCH PARENT THEN ONLY ONE ITEM WILL BE DISPLAYED BECAUSE THE ITEM WILL MATCH THE SIZE OF THE PARENT. HOWEVER, IF YOU DO WRAP CONTENT THEN THEY ALL WILL DISPLAY AS EACH ITEM IS JUST WRAPPING THE CONTENT.
     */
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position){
        Log.i(TAG,position+"");
        String name = nameList.get(position);
        holder.textView.setText(name);
    }

    @Override
    public int getItemCount(){
        if (nameList==null){
            return 0;
        }
        else {
            return nameList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public MyViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
