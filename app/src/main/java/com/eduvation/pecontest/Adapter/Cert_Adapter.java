package com.eduvation.pecontest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eduvation.pecontest.Class.Cert;
import com.eduvation.pecontest.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.eduvation.pecontest.R;
public class Cert_Adapter extends RecyclerView.Adapter {
    ArrayList<Cert> data=null;
    Context context;
    public Cert_Adapter(ArrayList<Cert> data, Context context){
        this.data=data;
        this.context=context;
    }
    public static class CertViewHolder extends RecyclerView.ViewHolder{
        TextView each_rank, each_name, each_count;
        ImageView each_img;
        VideoView each_video;
        public CertViewHolder(@NonNull View itemView) {
            super(itemView);
            each_rank=itemView.findViewById(R.id.each_rank);
            each_name=itemView.findViewById(R.id.each_name);
            each_count=itemView.findViewById(R.id.each_count);
            each_img=itemView.findViewById(R.id.each_img);
            each_video=itemView.findViewById(R.id.each_video);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cert_item, parent, false);
        CertViewHolder vh=new CertViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CertViewHolder vh=(CertViewHolder)holder;
        vh.each_rank.setText((position+1)+"등");
        vh.each_name.setText(data.get(position).getName());
        vh.each_count.setText(data.get(position).getCount()+"개");
        if(data.get(position).getType()==0){//image
            Glide.with(context).load(data.get(position).getData()).into(vh.each_img);
            vh.each_img.setVisibility(View.VISIBLE);
            vh.each_video.setVisibility(View.GONE);
        }
        else if(data.get(position).getType()==1){//video
            vh.each_video.setVideoURI(data.get(position).getData());
            vh.each_video.setVisibility(View.VISIBLE);
            vh.each_img.setVisibility(View.GONE);
            MediaController controller;
            controller=new MediaController(context);
            controller.setAnchorView(vh.each_video);
            vh.each_video.setMediaController(controller);
//            vh.each_video.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    int touch=0;
//                    if(event.getAction()==MotionEvent.ACTION_DOWN){
//                        if(touch==0){
//                            vh.each_video.start();
//                            touch=1;
//                        }
//                        else if(touch==1){
//                            vh.each_video.pause();
//                            touch=0;
//                        }
//                    }
//                    return true;
//                }
//            });

        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public void addnewItem(Cert ct){
        if(data==null){
            data=new ArrayList<>();
        }
        data.add(ct);
        Collections.sort(data, new CertComparator());
        notifyDataSetChanged();
    }
    public void sort(){
        if(data==null){
            data=new ArrayList<>();
        }
        else{
            Collections.sort(data, new CertComparator());
            notifyDataSetChanged();
        }
    }
    public class CertComparator implements Comparator<Cert> {
        @Override
        public int compare(Cert o1, Cert o2) {
            if(o1.getCount()>o2.getCount()){
                return -1;
            }
            if(o1.getCount()<o2.getCount()){
                return 1;
            }
            return 0;
        }
    }
}
