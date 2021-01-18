package com.eduvation.pecontest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Class.Five_average;

import java.util.ArrayList;

import com.eduvation.pecontest.R;
public class Average_Adapter extends RecyclerView.Adapter {
    ArrayList<Five_average> data=null;
    ArrayList<String> age_data=null;
    public Average_Adapter(ArrayList<Five_average> data, ArrayList<String> age_data){
        this.data=data;
        this.age_data=age_data;
    }
    public static class AverageViewHolder extends RecyclerView.ViewHolder{
        TextView age, c1, c2, c3, c4, c5;
        public AverageViewHolder(@NonNull View itemView) {
            super(itemView);
            age=itemView.findViewById(R.id.age);
            c1=itemView.findViewById(R.id.c1);
            c2=itemView.findViewById(R.id.c2);
            c3=itemView.findViewById(R.id.c3);
            c4=itemView.findViewById(R.id.c4);
            c5=itemView.findViewById(R.id.c5);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.average_item, parent, false);
        AverageViewHolder vh=new AverageViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AverageViewHolder vh=(AverageViewHolder)holder;
        vh.age.setText(age_data.get(position));
        vh.c1.setText(Integer.toString(data.get(position).getC1()));
        vh.c2.setText(Integer.toString(data.get(position).getC2()));
        vh.c3.setText(Integer.toString(data.get(position).getC3()));
        vh.c4.setText(Integer.toString(data.get(position).getC4()));
        vh.c5.setText(Integer.toString(data.get(position).getC5()));
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public void change(ArrayList<Five_average> d){
        if(data!=null){
            data=null;
        }
        data=new ArrayList<>();
        data=d;
        notifyDataSetChanged();
    }
}
