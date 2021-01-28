package com.eduvation.pecontest.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Adapter.Main_Rank_Adapter;
import com.eduvation.pecontest.Class.Area_Rank_Data;
import com.eduvation.pecontest.Class.Comment;
import com.eduvation.pecontest.Class.Communication;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Class.PE_average;
import com.eduvation.pecontest.Class.Total_score;
import com.eduvation.pecontest.Class.User;
import com.eduvation.pecontest.Login.LoginActivity;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageAverage;
import com.eduvation.pecontest.Singleton.ManageComment;
import com.eduvation.pecontest.Singleton.ManageCommunication;
import com.eduvation.pecontest.Singleton.ManageCompetition;
import com.eduvation.pecontest.Singleton.ManageTotalscore;
import com.eduvation.pecontest.Singleton.ManageUser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    RetrofitAPI myAPI;
    int get_match=0, get_communication=0, get_avg=0, get_all=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        myAPI= RetrofitClient.getApiService();
        getmatchData();
        getcommunicationData();
        getAvgData();
        getTopavglowData();
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
                    if(get_communication==1&&get_match==1&&get_avg==1&&get_all==1){
                        finish();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                }
                else{
                    System.out.println("match get fail");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Competition>> call, Throwable t) {
                System.out.println("match fail");
            }
        });
    }

    public void getcommunicationData(){
        ArrayList<Communication> total=new ArrayList<>();
        Call<ArrayList<Communication>> getcommute=myAPI.get_communication();
        getcommute.enqueue(new Callback<ArrayList<Communication>>() {
            @Override
            public void onResponse(Call<ArrayList<Communication>> call, Response<ArrayList<Communication>> response) {
                if(response.isSuccessful()){
                    for(Communication item:response.body()){
                        total.add(new Communication(item.getPk(), item.getHost_nickname(), item.getHost(), item.getLocation(), item.getTitle(), item.getFind_people(), item.getBody(), item.getCreated_at(), item.getCategory(), item.getAddress()));
                    }
                    ManageCommunication.getInstance().setCommunication_total(total);
                    get_communication=1;
                    if(get_communication==1&&get_match==1&&get_avg==1&&get_all==1){
                        finish();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    System.out.println("communication success");
                }
                else{
                    System.out.println("communication get fail");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Communication>> call, Throwable t) {
                System.out.println("communication fail");
            }
        });
    }

    public void getAvgData(){//나이대별 종목별 평균 수치
        Call<JsonArray> getavg=myAPI.get_average();
        getavg.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if(response.isSuccessful()){
                    JsonArray jsonArray=new JsonArray();
                    jsonArray=response.body();
                    PE_average test=new PE_average();
                    JsonArray one;

                    for(int i=0; i<jsonArray.size(); i++){
                        JsonElement each=jsonArray.get(i);
                        ArrayList<Integer> num;
                        switch(i){
                            case 0:
                                one=each.getAsJsonObject().get("M19to24").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM19to24(num);
                                break;
                            case 1:
                                one=each.getAsJsonObject().get("M25to29").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM25to29(num);
                                break;
                            case 2:
                                one=each.getAsJsonObject().get("M30to34").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM30to34(num);
                                break;
                            case 3:
                                one=each.getAsJsonObject().get("M35to39").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM35to39(num);
                                break;
                            case 4:
                                one=each.getAsJsonObject().get("M40to44").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM40to44(num);
                                break;
                            case 5:
                                one=each.getAsJsonObject().get("M45to49").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM45to49(num);
                                break;
                            case 6:
                                one=each.getAsJsonObject().get("M50to54").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM50to54(num);
                                break;
                            case 7:
                                one=each.getAsJsonObject().get("M55to59").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM55to59(num);
                                break;
                            case 8:
                                one=each.getAsJsonObject().get("M60to64").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setM60to64(num);
                                break;
                            case 9:
                                one=each.getAsJsonObject().get("F19to24").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF19to24(num);
                                break;
                            case 10:
                                one=each.getAsJsonObject().get("F25to29").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF25to29(num);
                                break;
                            case 11:
                                one=each.getAsJsonObject().get("F30to34").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF30to34(num);
                                break;
                            case 12:
                                one=each.getAsJsonObject().get("F35to39").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF35to39(num);
                                break;
                            case 13:
                                one=each.getAsJsonObject().get("F40to44").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF40to44(num);
                                break;
                            case 14:
                                one=each.getAsJsonObject().get("F45to49").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF45to49(num);
                                break;
                            case 15:
                                one=each.getAsJsonObject().get("F50to54").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF50to54(num);
                                break;
                            case 16:
                                one=each.getAsJsonObject().get("F55to59").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF55to59(num);
                                break;
                            case 17:
                                one=each.getAsJsonObject().get("F60to64").getAsJsonArray();
                                num=new ArrayList<>();
                                num.add(one.get(0).getAsInt());
                                num.add(one.get(1).getAsInt());
                                num.add(one.get(2).getAsInt());
                                num.add(one.get(3).getAsInt());
                                num.add(one.get(4).getAsInt());
                                test.setF60to64(num);
                                break;
                        }

                    }
                    System.out.println(test.getF25to29().get(0));
                    System.out.println("avg sucess");
                    ManageAverage.getInstance().setPe_average(test);
                    //System.out.println(ManageAverage.getInstance().getPe_average().getF25to29().get(0));
                    get_avg=1;
                    if(get_communication==1&&get_match==1&&get_avg==1&&get_all==1){
                        finish();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                }
                else{
                    System.out.println("avg get fail");
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                System.out.println(t);
                System.out.println("average fail");
            }
        });
    }
    public void getTopavglowData(){//종목별 금, 은, 동, 참가상의 각각 top, average, low
        ArrayList<Total_score> data=new ArrayList<>();
        Call<ArrayList<Total_score>> getall=myAPI.get_topavglow();
        getall.enqueue(new Callback<ArrayList<Total_score>>() {
            @Override
            public void onResponse(Call<ArrayList<Total_score>> call, Response<ArrayList<Total_score>> response) {
                if(response.isSuccessful()){
                    for(Total_score item:response.body()){
                        data.add(item);
                        System.out.println(item.getA1()+" "+item.getA2()+" "+item.getGender()+" "+item.getFive_gold_top());
                    }
                    ManageTotalscore.getInstance().setTotal(data);
                    System.out.println("topavglow success");
                    get_all=1;
                    if(get_communication==1&&get_match==1&&get_avg==1&&get_all==1){
                        finish();
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                }
                else{
                    System.out.println("topavglow get fail");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Total_score>> call, Throwable t) {
                System.out.println("get all data fail");
            }
        });
    }
}
