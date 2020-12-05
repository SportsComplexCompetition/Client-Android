package com.eduvation.pecontest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageComment;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EachMeetingActivity extends AppCompatActivity {
    TextView fortest, commenttest;
    EditText editcomment;
    Button comment_btn;
    Intent intent;
    String title, location, body, address, date;
    int pk;

    RetrofitAPI myAPI;
    ArrayList<Comment> now_comment=null;

    String test_comment="";
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
        fortest=findViewById(R.id.fortest);
        commenttest=findViewById(R.id.commenttest);
        editcomment=findViewById(R.id.editcomment);
        comment_btn=findViewById(R.id.comment_btn);

        intent=getIntent();
        title=intent.getStringExtra("title");
        location=intent.getStringExtra("location");
        body=intent.getStringExtra("body");
        address=intent.getStringExtra("address");
        date=intent.getStringExtra("date");
        pk=intent.getIntExtra("pk", -1);
        fortest.setText(title+"\n"+location+"\n"+body+"\n"+address+"\n"+date+"\n"+pk);
    }

    public void setting_comment(){
        now_comment=new ArrayList<>();
        myAPI= RetrofitClient.getApiService();
//        Call<ArrayList<Comment>> getcomment=myAPI.get_comment();
//        getcomment.enqueue(new Callback<ArrayList<Comment>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
//                if(response.isSuccessful()){
//                    for(Comment item:response.body()){
//                        if(pk==item.getMeeting()){
//                            now_comment.add(item);
//                            test_comment=test_comment+item.getContent()+" "+item.getUser_nickname()+"\n";
//                        }
//                    }
//                    commenttest.setText(test_comment);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
//
//            }
//        });
        ArrayList<Comment> total=new ArrayList<>();
        total= ManageComment.getInstance().getComment_total();
        for(int i=0; i<total.size(); i++){
            if(pk==total.get(i).getMeeting()){
                now_comment.add(total.get(i));
                test_comment=test_comment+total.get(i).getContent()+" "+total.get(i).getUser_nickname()+"\n";
            }
        }
        commenttest.setText(test_comment);
    }

    public void writing_comment(){
        String text="";
        text=editcomment.getText().toString().trim();
        if(TextUtils.isEmpty(text)||text.length()==0||text==""||text==" "){
            return;
        }
        final String tt=text;
        Date d=new Date();
        Comment mycomment=new Comment("mymy", 3, pk, tt, d);
        Call<Void> newcomment=myAPI.send_comment(mycomment);
        newcomment.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                test_comment=test_comment+tt+" "+"mymy\n";
                commenttest.setText("");
                commenttest.setText(test_comment);
                editcomment.setText("");
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
