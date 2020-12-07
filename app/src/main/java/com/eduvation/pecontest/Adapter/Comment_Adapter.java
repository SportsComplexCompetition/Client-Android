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
import com.eduvation.pecontest.Class.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.eduvation.pecontest.R;
public class Comment_Adapter extends RecyclerView.Adapter {
    ArrayList<Integer> comment_img=null;
    ArrayList<Comment> data=null;
    Context context;
    public Comment_Adapter(ArrayList<Integer> comment_img, ArrayList<Comment> data, Context context){
        this.comment_img=comment_img;
        this.data=data;
        this.context=context;
    }
    public static class CommentViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name, body, date;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.comment_img);
            name=itemView.findViewById(R.id.comment_name);
            body=itemView.findViewById(R.id.comment_body);
            date=itemView.findViewById(R.id.comment_date);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        CommentViewHolder vh=new CommentViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommentViewHolder vh=(CommentViewHolder)holder;
        if(position<3){
            Glide.with(context).load(comment_img.get(0)).into(vh.img);
        }
        else{
            Glide.with(context).load(comment_img.get(1)).into(vh.img);
        }
        vh.name.setText(data.get(position).getUser_nickname());
        vh.body.setText(data.get(position).getContent());
        Date date=data.get(position).getCreated_at();
        SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd");
        String d=format.format(date);
        vh.date.setText(d);
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public void addnewItem(Comment comment){
        if(data==null){
            data=new ArrayList<>();
        }
        data.add(comment);
        notifyDataSetChanged();
    }
}
