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
    ArrayList<Integer> img=null;
    Context context;
    public Communicate_Adapter(ArrayList<Communication> data, ArrayList<Integer> img, Context context){
        this.data=data;
        this.img=img;
        this.context=context;
    }
    public static class CommunicationViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title, location, people;
        public CommunicationViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.commute_background);
            title=itemView.findViewById(R.id.commute_title);
            location=itemView.findViewById(R.id.commute_location);
            people=itemView.findViewById(R.id.commute_people);
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
        Glide.with(context).load(img.get(position)).into(vh.img);
        vh.title.setText(data.get(position).getTitle());
        String loc="";
        int l=data.get(position).getLocation();
        switch(l){
            case 0:loc="서울";break;
            case 1:loc="대구";break;
            case 2:loc="대전";break;
            case 3:loc="광주";break;
            case 4:loc="인천";break;
            case 5:loc="부산";break;
            case 6:loc="울산";break;
            case 7:loc="세종";break;
            case 8:loc="제주";break;
            case 9:loc="경기";break;
            case 10:loc="강원";break;
            case 11:loc="충남";break;
            case 12:loc="충북";break;
            case 13:loc="전남";break;
            case 14:loc="전북";break;
            case 15:loc="경남";break;
            case 16:loc="경북";break;
        }
        vh.location.setText(loc);
        vh.people.setText(data.get(position).getFind_people()+"명 참여중");
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
