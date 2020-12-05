package com.eduvation.pecontest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Class.Area_Rank_Data;

import java.util.ArrayList;
import com.eduvation.pecontest.R;

public class Main_Rank_Adapter extends RecyclerView.Adapter {
    ArrayList<Area_Rank_Data> data=null;
    Context context;
    public Main_Rank_Adapter(ArrayList<Area_Rank_Data> data, Context context){
        this.data=data;
        this.context=context;
    }
    public static class MainRankViewHolder extends RecyclerView.ViewHolder{
        TextView loc_rank, loc_medal, matching;
        public MainRankViewHolder(@NonNull View itemView) {
            super(itemView);
            loc_rank=itemView.findViewById(R.id.location_rank);
            loc_medal=itemView.findViewById(R.id.location_medal);
            matching=itemView.findViewById(R.id.matching);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_rank_item, parent, false);
        MainRankViewHolder vh=new MainRankViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainRankViewHolder vh=(MainRankViewHolder)holder;
        vh.loc_rank.setText((position+1)+"위-"+data.get(position).getLocation());
        String medal="";
        medal=medal+"금상 : "+data.get(position).getGold()+"회\n\n";
        medal=medal+"은상 : "+data.get(position).getSilver()+"회\n\n";
        medal=medal+"동상 : "+data.get(position).getBronze()+"회\n\n";
        vh.loc_medal.setText(medal);
        vh.matching.setText(data.get(position).getMatch()+"회 매칭");
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
}
