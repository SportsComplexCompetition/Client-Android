package com.eduvation.pecontest.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Class.User;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageComment;
import com.eduvation.pecontest.Singleton.ManageCompetition;
import com.eduvation.pecontest.Singleton.ManageUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    RetrofitAPI myAPI;
    String nickname="", email="";
    int location=-1;
    String loc="";
    int mypk=6;
    int get_match=0, get_comment=0, get_user=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        myAPI= RetrofitClient.getApiService();
        getcommentData();
        getmatchData();
        getUserData();
    }
    public void getcommentData(){
        ArrayList<Comment> comment_total=new ArrayList<>();
        Call<ArrayList<Comment>> getcomment=myAPI.get_comment();
        getcomment.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                if(response.isSuccessful()){
                    for(Comment item:response.body()){
                        comment_total.add(item);
                    }
                    get_comment=1;
                    ManageComment.getInstance().setComment_total(comment_total);
                    if(get_comment==1&&get_match==1&&get_user==1){
                        finish();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                System.out.println("comment fail");
            }
        });
    }

    public void getmatchData(){

        ArrayList<Competition> events=new ArrayList<>();
        ArrayList<Competition> matches=new ArrayList<>();
        Call<ArrayList<Competition>> comp_total=myAPI.get_competition();
        comp_total.enqueue(new Callback<ArrayList<Competition>>() {
            @Override
            public void onResponse(Call<ArrayList<Competition>> call, Response<ArrayList<Competition>> response) {
                if(response.isSuccessful()){
                    int people=-1;
                    Competition best=new Competition();
                    ArrayList<Integer> count=new ArrayList<>();
                    for(int i=0; i<17; i++){
                        count.add(0);
                    }
                    for(Competition item:response.body()){
                        if(item.getComp_type()==1){
                            events.add(item);
                            if(people==-1){
                                people=item.getJoined_people().size();
                                best=item;
                            }
                            else{
                                if(people<item.getJoined_people().size()){
                                    best=item;
                                }
                            }
                        }
                        else if(item.getComp_type()==0){
                            matches.add(item);
                            int now=count.get(item.getLocation());
                            count.set(item.getLocation(), now+1);
                        }
                    }
                    ManageCompetition.getInstance().setEvent_total(events);
                    ManageCompetition.getInstance().setMatch_total(matches);
                    ManageCompetition.getInstance().setBest(best);
                    ManageCompetition.getInstance().setMatch_count(count);
                    System.out.println("match success");
                    get_match=1;
                    if(get_comment==1&&get_match==1&&get_user==1){
                        finish();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Competition>> call, Throwable t) {
                System.out.println("match fail");
            }
        });
    }

    public void getUserData(){
        Call<ArrayList<User>> getuser=myAPI.get_user();
        getuser.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(response.isSuccessful()){
                    for(User item:response.body()){
                        if(mypk==item.getPk()){
                            nickname=item.getNickname();
                            email=item.getEmail();
                            location=item.getLocation();
                            switch(location){
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

                            ManageUser.getInstance().setMe(item);
                            break;
                        }
                    }
                    get_user=1;
                    if(get_comment==1&&get_match==1&&get_user==1){
                        finish();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }
}
