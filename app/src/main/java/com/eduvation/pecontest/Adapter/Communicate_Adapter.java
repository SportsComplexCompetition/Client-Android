package com.eduvation.pecontest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eduvation.pecontest.Class.Communication;

import java.util.ArrayList;
import com.eduvation.pecontest.R;

public class Communicate_Adapter extends RecyclerView.Adapter {
    ArrayList<Communication> data=null;
    Context context;
    public Communicate_Adapter(ArrayList<Communication> data, Context context){
        this.data=data;
        this.context=context;
    }
    public static class CommunicationViewHolder extends RecyclerView.ViewHolder{
        TextView test;
        public CommunicationViewHolder(@NonNull View itemView) {
            super(itemView);
            test=itemView.findViewById(R.id.test);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.communication_item, parent, false);
        CommunicationViewHolder vh=new CommunicationViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommunicationViewHolder vh=(CommunicationViewHolder)holder;
        vh.test.setText(data.get(position).getBody()+data.get(position).getDate()+data.get(position).getHost_email());
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public void removeall(ArrayList<Communication> newData){
        if(data!=null){
            data=null;
        }
        data=newData;
        notifyDataSetChanged();
    }
}
