package com.eduvation.pecontest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eduvation.pecontest.Class.Main_Challenge;

import java.util.ArrayList;
import com.eduvation.pecontest.R;

public class Main_Challenge_Adapter extends RecyclerView.Adapter {
    ArrayList<Main_Challenge> data=null;
    Context context;
    public Main_Challenge_Adapter(ArrayList<Main_Challenge> data, Context context){
        this.data=data;
        this.context=context;
    }
    public static class MainChallengeViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView cat, period, people;
        CardView card;
        public MainChallengeViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.main_ch_img);
            cat=itemView.findViewById(R.id.main_ch_cat);
            period=itemView.findViewById(R.id.main_ch_period);
            people=itemView.findViewById(R.id.main_ch_people);
            card=itemView.findViewById(R.id.card);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_challenge_item, parent, false);
        MainChallengeViewHolder vh=new MainChallengeViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainChallengeViewHolder vh=(MainChallengeViewHolder)holder;
        vh.cat.setText(data.get(position).getCat());
        vh.people.setText(data.get(position).getPeople()+"명이 함께하는 중");
        Glide.with(context).load(data.get(position).getImg()).into(vh.img);
        vh.period.setText(data.get(position).getPeriod());
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
}
