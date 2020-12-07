package com.eduvation.pecontest.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eduvation.pecontest.Class.Competition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.eduvation.pecontest.Class.NewAttend;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventAdapter extends RecyclerView.Adapter {
    ArrayList<Integer> img=null;
    ArrayList<Competition> events=null;
    Context context;
    public EventAdapter(ArrayList<Competition> events, ArrayList<Integer> img, Context context){
        this.events=events;
        this.img=img;
        this.context=context;
    }
    public static class EventViewHolder extends RecyclerView.ViewHolder{
        ImageView event_img;
        TextView event_period, event_category, event_people;
        Button event_attend_btn;
        TextView event_money, event_total_money;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            event_img=itemView.findViewById(R.id.event_img);
            event_period=itemView.findViewById(R.id.event_period);
            event_category=itemView.findViewById(R.id.event_category);
            event_people=itemView.findViewById(R.id.event_people);
            event_attend_btn=itemView.findViewById(R.id.event_attend_btn);
            event_money=itemView.findViewById(R.id.event_money);
            event_total_money=itemView.findViewById(R.id.event_total_money);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        EventViewHolder vh=new EventViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EventViewHolder vh=(EventViewHolder)holder;
        final int pos=position;
        Glide.with(context).load(img.get(position%4)).into(vh.event_img);
        SimpleDateFormat from=new SimpleDateFormat("MM.dd");
        SimpleDateFormat to=new SimpleDateFormat("MM.dd");
        String f=from.format(events.get(position).getCreated_at());
        String t=to.format(events.get(position).getEnded_at());
        vh.event_period.setText(f+"~"+t);
        vh.event_category.setText(events.get(position).getCategory());
        vh.event_people.setText(events.get(position).getJoined_people().size()+"명 참여중");
        vh.event_money.setText("참가비"+events.get(position).getMoney()+"원");
        vh.event_total_money.setText("누적금액"+events.get(position).getMoney()*events.get(position).getJoined_people().size()+"원");
        for(int i=0; i<events.get(position).getJoined_people().size(); i++){
            if(6==events.get(position).getJoined_people().get(i)){
                vh.event_attend_btn.setText("참여중");
                vh.event_attend_btn.setBackground(ContextCompat.getDrawable(context, R.drawable.attend_btn));
            }
        }
        if(6==events.get(position).getHost()){
            vh.event_attend_btn.setText("참여중");
            vh.event_attend_btn.setBackground(ContextCompat.getDrawable(context, R.drawable.attend_btn));
        }
        if(vh.event_attend_btn.getText().equals("참가하기")){
            vh.event_attend_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("참가하시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    RetrofitAPI myAPI= RetrofitClient.getApiService();
                                    NewAttend attend=new NewAttend(6, events.get(pos).getMoney());
                                    Call<Void> newattend=myAPI.join_competition(events.get(pos).getId(), attend);
                                    newattend.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            vh.event_attend_btn.setText("참여중");
                                            vh.event_attend_btn.setBackground(ContextCompat.getDrawable(context, R.drawable.attend_btn));
                                            vh.event_people.setText(events.get(pos).getJoined_people().size()+1+"명 참여중");

                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {

                                        }
                                    });
                                }
                            })
                            .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return events==null?0:events.size();
    }
}
