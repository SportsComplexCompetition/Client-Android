package com.eduvation.pecontest.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eduvation.pecontest.Adapter.Comment_Adapter;
import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageComment;
import com.eduvation.pecontest.Singleton.ManageUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EachMeetingActivity extends AppCompatActivity {
    ImageView each_img;
    TextView each_title, each_loc_address, each_body, each_date, each_name, each_category;
    RecyclerView each_comment_recycle;
    TextView no_comment;
    EditText edit_comment;
    ImageView comment_btn;
    Intent intent;
    String title, location, body, address, date, name, category;
    int pk;
    Context each_context;

    RetrofitAPI myAPI;
    ArrayList<Comment> now_comment=null;
    ArrayList<Integer> img=null;
    Comment_Adapter comment_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.each_meeting);

        setting_view();
        setting_comment();
        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writing_comment();
            }
        });


    }
    public void setting_view(){
        each_img=findViewById(R.id.each_img);
        each_title=findViewById(R.id.each_title);
        each_loc_address=findViewById(R.id.each_loc_address);
        each_body=findViewById(R.id.each_body);
        each_date=findViewById(R.id.each_date);
        each_name=findViewById(R.id.each_name);
        each_category=findViewById(R.id.each_category);
        each_comment_recycle=findViewById(R.id.each_comment_recycle);
        no_comment=findViewById(R.id.no_comment);
        edit_comment=findViewById(R.id.edit_comment);
        comment_btn=findViewById(R.id.comment_btn);
        each_context=this;

        intent=getIntent();
        title=intent.getStringExtra("title");
        location=intent.getStringExtra("location");
        body=intent.getStringExtra("body");
        address=intent.getStringExtra("address");
        date=intent.getStringExtra("date");
        pk=intent.getIntExtra("pk", -1);
        name=intent.getStringExtra("nickname");
        category=intent.getStringExtra("category");

        Glide.with(each_context).load(intent.getIntExtra("img", 0)).into(each_img);
        each_title.setText(title);
        each_loc_address.setText(location+" "+address);
        each_body.setText(body);
        each_date.setText("SINCE "+date);
        each_name.setText(name);
        each_category.setText(category);

    }

    public void setting_comment(){
        now_comment=new ArrayList<>();
        myAPI= RetrofitClient.getApiService();

        img=new ArrayList<>();
        img.add(R.drawable.who2);
        img.add(R.drawable.who3);

        ArrayList<Comment> total=new ArrayList<>();
        total= ManageComment.getInstance().getComment_total();
        for(int i=0; i<total.size(); i++){
            if(pk==total.get(i).getMeeting()){
                now_comment.add(total.get(i));
            }
        }

        each_comment_recycle.setLayoutManager(new LinearLayoutManager(each_context));
        comment_adapter=new Comment_Adapter(img, now_comment, each_context);
        each_comment_recycle.setAdapter(comment_adapter);
        if(now_comment.size()==0){
            no_comment.setVisibility(View.VISIBLE);
        }
    }

    public void writing_comment(){
        String text="";
        text=edit_comment.getText().toString().trim();
        if(TextUtils.isEmpty(text)||text.length()==0||text==""||text==" "){
            return;
        }
        final String tt=text;
        Date d=new Date();
        Comment mycomment=new Comment(ManageUser.getInstance().getMe().getNickname(), 3, pk, tt, d);
        Call<Void> newcomment=myAPI.send_comment(mycomment);
        newcomment.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                no_comment.setVisibility(View.GONE);
                edit_comment.setText("");
                Date current= Calendar.getInstance().getTime();
//                SimpleDateFormat simpleDate=new SimpleDateFormat("yyyy.MM.dd");
//                String dd=simpleDate.format(current);
                mycomment.setCreated_at(current);
                comment_adapter.addnewItem(mycomment);
                ManageComment.getInstance().addnewComment(mycomment);
                System.out.println("new comment success");

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("comment fail");
            }
        });

    }
}
