package com.eduvation.pecontest.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.eduvation.pecontest.Class.Communication;
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

public class MatchAdapter extends RecyclerView.Adapter {
    ArrayList<Integer> img=null;
    ArrayList<Competition> matches=null;
    Context context;
    public MatchAdapter(ArrayList<Competition> matches, ArrayList<Integer> img, Context context){
        this.matches=matches;
        this.img=img;
        this.context=context;
    }
    public static class MatchViewHolder extends RecyclerView.ViewHolder{
        ImageView match_img;
        TextView match_period, match_location, match_category, match_people;
        Button match_attend_btn;
        TextView match_money, match_host, match_money_total;
        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            match_img=itemView.findViewById(R.id.match_img);
            match_period=itemView.findViewById(R.id.match_period);
            match_location=itemView.findViewById(R.id.match_location);
            match_category=itemView.findViewById(R.id.match_category);
            match_people=itemView.findViewById(R.id.match_people);
            match_attend_btn=itemView.findViewById(R.id.match_attend_btn);
            match_money=itemView.findViewById(R.id.match_money);
            match_host=itemView.findViewById(R.id.match_host);
            match_money_total=itemView.findViewById(R.id.match_money_total);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_item, parent, false);
        MatchViewHolder vh=new MatchViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MatchViewHolder vh=(MatchViewHolder)holder;
        final int pos=position;
        SimpleDateFormat from=new SimpleDateFormat("MM.dd");
        SimpleDateFormat to=new SimpleDateFormat("MM.dd");
        String f=from.format(matches.get(position).getCreated_at());
        String t=from.format(matches.get(position).getEnded_at());
        Glide.with(context).load(img.get(position%4)).into(vh.match_img);
        vh.match_period.setText(f+"~"+t);
        vh.match_category.setText(matches.get(position).getCategory());
        vh.match_people.setText("참가자"+matches.get(position).getJoined_people().size()+"명/"+matches.get(position).getMax_people()+"명");
        vh.match_money.setText("참가비"+matches.get(position).getMoney()+"원");
        vh.match_money_total.setText("누적금액"+matches.get(position).getMoney()*matches.get(position).getJoined_people().size()+"원");
        for(int i=0; i<matches.get(position).getJoined_people().size(); i++){
            if(6==matches.get(position).getJoined_people().get(i)){
                vh.match_attend_btn.setText("참여중");
                vh.match_attend_btn.setBackground(ContextCompat.getDrawable(context, R.drawable.attend_btn));
            }
        }
        if(6==matches.get(position).getHost()){
            vh.match_attend_btn.setText("참여중");
            vh.match_attend_btn.setBackground(ContextCompat.getDrawable(context, R.drawable.attend_btn));
        }
        if(vh.match_attend_btn.getText().equals("참가하기")){
            vh.match_attend_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    builder.setMessage("참가하시겠습니까?")
                            .setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    RetrofitAPI myAPI= RetrofitClient.getApiService();
                                    NewAttend attend=new NewAttend(6, matches.get(pos).getMoney());
                                    Call<Void> newattend=myAPI.join_competition(matches.get(pos).getId(), attend);
                                    newattend.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            vh.match_attend_btn.setText("참여중");
                                            vh.match_attend_btn.setBackground(ContextCompat.getDrawable(context, R.drawable.attend_btn));
                                            vh.match_people.setText("참가자"+matches.get(position).getJoined_people().size()+"명/"+matches.get(position).getMax_people()+"명");

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
        return matches==null?0:matches.size();
    }

    public void addnewItem(Competition c){
        if(matches==null){
            matches=new ArrayList<>();
        }
        matches.add(c);
        notifyDataSetChanged();
    }
}
