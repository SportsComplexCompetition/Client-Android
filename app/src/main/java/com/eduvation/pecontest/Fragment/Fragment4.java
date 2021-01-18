package com.eduvation.pecontest.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Adapter.Average_Adapter;
import com.eduvation.pecontest.Adapter.MyMatchAdapter;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Class.Five_average;
import com.eduvation.pecontest.Class.PE_average;
import com.eduvation.pecontest.Class.Total_score;
import com.eduvation.pecontest.Class.User;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageAverage;
import com.eduvation.pecontest.Singleton.ManageCompetition;
import com.eduvation.pecontest.Singleton.ManageTotalscore;
import com.eduvation.pecontest.Singleton.ManageUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment4 extends Fragment {
    TextView my_name, my_age, my_email, my_gender, my_location;
    Button check_btn;
    Button male, female;
    RecyclerView average;
    ImageView no_match_img;
    RecyclerView my_match;
    String nickname="", email="";
    int location=-1;
    String loc="";
    int gender=0;
    int myage=-1;
    Context fr4_context;

    LinearLayout after_check_layout;
    LinearLayout check_c1, check_c2, check_c3, check_c4, check_c5;
    TextView c1_rank, c2_rank, c3_rank, c4_rank, c5_rank;
    LinearLayout after_check;
    TextView total_rank;
    ImageView recheck;

    ArrayList<Integer> img=null;
    ArrayList<Competition> my_comp=null;
    public MyMatchAdapter myadapter=null;
    int mypk=6;
    int g=0;
    User my=null;
    RetrofitAPI myAPI;

    AlertDialog check_dialog;
    LinearLayout check;
    EditText ch1, ch2, ch3, ch4, ch5;
    TextView submit;
    String c1="", c2="", c3="", c4="", c5="";
    int cc1, cc2, cc3, cc4, cc5;
    String total_medal, m1, m2, m3, m4, m5;
    int b1, b2, b3, b4, b5;
    int w1, w2, w3, w4, w5;
    int a1, a2, a3, a4, a5;
    String ment1="", ment2="", ment3="", ment4="", ment5="";
    int p1=0, p2=0, p3=0, p4=0, p5=0;

    AlertDialog result_dialog;
    LinearLayout resultcheck;
    TextView each_medal, rank_best, best, rank_worst, worst, rank_avg, avg, advice;
    TextView check_name;
    Total_score analysis=null;

    ArrayList<String> age;
    ArrayList<Five_average> avg_male;
    ArrayList<Five_average> avg_female;
    Average_Adapter avg_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment4, container, false);

        my_name=v.findViewById(R.id.my_name);
        my_age=v.findViewById(R.id.my_age);
        my_email=v.findViewById(R.id.my_email);
        my_gender=v.findViewById(R.id.my_gender);
        check_btn=v.findViewById(R.id.check_btn);
        no_match_img=v.findViewById(R.id.no_match_img);
        my_match=v.findViewById(R.id.my_match);
        my_location=v.findViewById(R.id.my_location);
        male=v.findViewById(R.id.male_btn);
        female=v.findViewById(R.id.female_btn);
        average=v.findViewById(R.id.average_recyclerview);
        fr4_context=getContext();
        male.setTextColor(Color.WHITE);
        male.setBackgroundResource(R.drawable.color_round_layout);
        female.setTextColor(Color.parseColor("#0C2A55"));
        female.setBackgroundResource(R.drawable.round_btn);

        after_check_layout=v.findViewById(R.id.after_check_layout);
        check_c1=v.findViewById(R.id.check_c1);
        check_c2=v.findViewById(R.id.check_c2);
        check_c3=v.findViewById(R.id.check_c3);
        check_c4=v.findViewById(R.id.check_c4);
        check_c5=v.findViewById(R.id.check_c5);
        c1_rank=v.findViewById(R.id.c1_rank);
        c2_rank=v.findViewById(R.id.c2_rank);
        c3_rank=v.findViewById(R.id.c3_rank);
        c4_rank=v.findViewById(R.id.c4_rank);
        c5_rank=v.findViewById(R.id.c5_rank);
        after_check=v.findViewById(R.id.after_check);
        total_rank=v.findViewById(R.id.total_rank);
        recheck=v.findViewById(R.id.recheck_btn);

        getting_myinfo();
        setting_gender_btn();
        setting_average();
        setting_recyclerview();
        setting_check_popup();
        setting_result_popup();
        setting_check_btn();
        setting_result_btn();


        return v;
    }

    public void getting_myinfo(){
//        myAPI= RetrofitClient.getApiService();
//        Call<ArrayList<User>> getuser=myAPI.get_user();
//        getuser.enqueue(new Callback<ArrayList<User>>() {
//            @Override
//            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
//                if(response.isSuccessful()){
//                    for(User item:response.body()){
//                        if(mypk==item.getPk()){
//                            nickname=item.getNickname();
//                            email=item.getEmail();
//                            location=item.getLocation();
//                            switch(location){
//                                case 0:loc="서울";break;
//                                case 1:loc="대구";break;
//                                case 2:loc="대전";break;
//                                case 3:loc="광주";break;
//                                case 4:loc="인천";break;
//                                case 5:loc="부산";break;
//                                case 6:loc="울산";break;
//                                case 7:loc="세종";break;
//                                case 8:loc="제주";break;
//                                case 9:loc="경기";break;
//                                case 10:loc="강원";break;
//                                case 11:loc="충남";break;
//                                case 12:loc="충북";break;
//                                case 13:loc="전남";break;
//                                case 14:loc="전북";break;
//                                case 15:loc="경남";break;
//                                case 16:loc="경북";break;
//                            }
//                            my_name.setText(nickname);
//                            my_email.setText(email);
//                            break;
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
//
//            }
//        });
        my=new User();
        my= ManageUser.getInstance().getMe();
        nickname=my.getNickname();
        email=my.getEmail();
        myage=my.getAge();
        location=my.getLocation();
        switch (location){
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
        my_name.setText(nickname);
        my_email.setText(email);
        my_location.setText(loc);
        my_age.setText(myage+"세");
        if(my.getSex().equals("male")){
            my_gender.setText("남자");
            g=0;
        }
        else if(my.getSex().equals("female")){
            my_gender.setText("여자");
            g=1;
        }
        analysis=new Total_score();
        ArrayList<Total_score> all=new ArrayList<>();
        all= ManageTotalscore.getInstance().getTotal();
        for(int i=0; i<all.size(); i++){
            if(myage>=all.get(i).getA1()&&myage<=all.get(i).getA2()&&g==all.get(i).getGender()){
                analysis=all.get(i);
                break;
            }
        }
    }
    public void setting_average(){
        age=new ArrayList<>();
        age.add("19~24세");age.add("25~29세");age.add("30~34세");age.add("35~39세");age.add("40~44세");age.add("45~49세");age.add("50~54세");age.add("55~59세");age.add("60~64세");
        avg_male=new ArrayList<>();
        avg_female=new ArrayList<>();
        PE_average t=new PE_average();
        t=ManageAverage.getInstance().getPe_average();
        avg_male.add(new Five_average(t.getM19to24().get(0), t.getM19to24().get(1), t.getM19to24().get(2), t.getM19to24().get(3), t.getM19to24().get(4)));
        avg_male.add(new Five_average(t.getM25to29().get(0), t.getM25to29().get(1), t.getM25to29().get(2), t.getM25to29().get(3), t.getM25to29().get(4)));
        avg_male.add(new Five_average(t.getM30to34().get(0), t.getM30to34().get(1), t.getM30to34().get(2), t.getM30to34().get(3), t.getM30to34().get(4)));
        avg_male.add(new Five_average(t.getM35to39().get(0), t.getM35to39().get(1), t.getM35to39().get(2), t.getM35to39().get(3), t.getM35to39().get(4)));
        avg_male.add(new Five_average(t.getM40to44().get(0), t.getM40to44().get(1), t.getM40to44().get(2), t.getM40to44().get(3), t.getM40to44().get(4)));
        avg_male.add(new Five_average(t.getM45to49().get(0), t.getM45to49().get(1), t.getM45to49().get(2), t.getM45to49().get(3), t.getM45to49().get(4)));
        avg_male.add(new Five_average(t.getM50to54().get(0), t.getM50to54().get(1), t.getM50to54().get(2), t.getM50to54().get(3), t.getM50to54().get(4)));
        avg_male.add(new Five_average(t.getM55to59().get(0), t.getM55to59().get(1), t.getM55to59().get(2), t.getM55to59().get(3), t.getM55to59().get(4)));
        avg_male.add(new Five_average(t.getM60to64().get(0), t.getM60to64().get(1), t.getM60to64().get(2), t.getM60to64().get(3), t.getM60to64().get(4)));
        avg_female.add(new Five_average(t.getF19to24().get(0), t.getF19to24().get(1), t.getF19to24().get(2), t.getF19to24().get(3), t.getF19to24().get(4)));
        avg_female.add(new Five_average(t.getF25to29().get(0), t.getF25to29().get(1), t.getF25to29().get(2), t.getF25to29().get(3), t.getF25to29().get(4)));
        avg_female.add(new Five_average(t.getF30to34().get(0), t.getF30to34().get(1), t.getF30to34().get(2), t.getF30to34().get(3), t.getF30to34().get(4)));
        avg_female.add(new Five_average(t.getF35to39().get(0), t.getF35to39().get(1), t.getF35to39().get(2), t.getF35to39().get(3), t.getF35to39().get(4)));
        avg_female.add(new Five_average(t.getF40to44().get(0), t.getF40to44().get(1), t.getF40to44().get(2), t.getF40to44().get(3), t.getF40to44().get(4)));
        avg_female.add(new Five_average(t.getF45to49().get(0), t.getF45to49().get(1), t.getF45to49().get(2), t.getF45to49().get(3), t.getF45to49().get(4)));
        avg_female.add(new Five_average(t.getF50to54().get(0), t.getF50to54().get(1), t.getF50to54().get(2), t.getF50to54().get(3), t.getF50to54().get(4)));
        avg_female.add(new Five_average(t.getF55to59().get(0), t.getF55to59().get(1), t.getF55to59().get(2), t.getF55to59().get(3), t.getF55to59().get(4)));
        avg_female.add(new Five_average(t.getF60to64().get(0), t.getF60to64().get(1), t.getF60to64().get(2), t.getF60to64().get(3), t.getF60to64().get(4)));
        average.setLayoutManager(new LinearLayoutManager(getContext()));
        avg_adapter=new Average_Adapter(avg_male, age);
        average.setAdapter(avg_adapter);
    }
    public void setting_recyclerview(){
        ArrayList<Competition> total=new ArrayList<>();
        total= ManageCompetition.getInstance().getMatch_total();
        img=new ArrayList<>();
        my_comp=new ArrayList<>();
        img.add(R.drawable.match1);img.add(R.drawable.match2);img.add(R.drawable.match3);img.add(R.drawable.match4);
        for(int i=0; i<total.size(); i++){
            for(int j=0; j<total.get(i).getJoined_people().size(); j++){
                if(mypk==total.get(i).getJoined_people().get(j)){
                    my_comp.add(total.get(i));
                    break;
                }
            }
        }
        if(my_comp.size()==0){
            no_match_img.setVisibility(View.VISIBLE);
        }
        my_match.setLayoutManager(new GridLayoutManager(fr4_context, 2));
        myadapter=new MyMatchAdapter(my_comp, img, fr4_context);
        my_match.setAdapter(myadapter);
    }
    public void addnewMatch(){
        if(myadapter.getItemCount()>=1){
            no_match_img.setVisibility(View.GONE);
        }
    }

    public void setting_gender_btn(){
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender==1){
                    gender=0;
                    female.setTextColor(Color.parseColor("#0C2A55"));
                    male.setTextColor(Color.WHITE);
                    male.setBackgroundResource(R.drawable.color_round_layout);
                    female.setBackgroundResource(R.drawable.round_btn);
                    avg_adapter.change(avg_male);
                }
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gender==0){
                    gender=1;
                    male.setTextColor(Color.parseColor("#0C2A55"));
                    female.setTextColor(Color.WHITE);
                    female.setBackgroundResource(R.drawable.color_round_layout);
                    male.setBackgroundResource(R.drawable.round_btn);
                    avg_adapter.change(avg_female);
                }
            }
        });
    }
    public void setting_check_btn(){
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ch=new AlertDialog.Builder(fr4_context);
                if(check.getParent()!=null){
                    ((ViewGroup)check.getParent()).removeView(check);
                }
                ch.setView(check);
                check_dialog=ch.create();
                check_dialog.show();
            }
        });
        recheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ch=new AlertDialog.Builder(fr4_context);
                if(check.getParent()!=null){
                    ((ViewGroup)check.getParent()).removeView(check);
                }
                ch.setView(check);
                check_dialog=ch.create();
                check_dialog.show();
            }
        });

    }
    public void setting_check_popup(){
        check=(LinearLayout)View.inflate(fr4_context, R.layout.my_pe_check_popup, null);
        ch1=check.findViewById(R.id.check1);
        ch2=check.findViewById(R.id.check2);
        ch3=check.findViewById(R.id.check3);
        ch4=check.findViewById(R.id.check4);
        ch5=check.findViewById(R.id.check5);
        submit=check.findViewById(R.id.my_check);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1=ch1.getText().toString().trim();
                c2=ch2.getText().toString().trim();
                c3=ch3.getText().toString().trim();
                c4=ch4.getText().toString().trim();
                c5=ch5.getText().toString().trim();
                if(TextUtils.isEmpty(c1)||TextUtils.isEmpty(c2)||TextUtils.isEmpty(c3)||TextUtils.isEmpty(c4)||TextUtils.isEmpty(c5)){
                    Toast.makeText(fr4_context, "기록을 다 채워주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                cc1=Integer.parseInt(c1);
                cc2=Integer.parseInt(c2);
                cc3=Integer.parseInt(c3);
                cc4=Integer.parseInt(c4);
                cc5=Integer.parseInt(c5);
                int i1=0, i2=0, i3=0, i4=0, i5=0;
                if(cc1>=analysis.getOne_gold_low()){
                    m1="금상";
                    i1=4;
                    b1=analysis.getOne_gold_top();w1=analysis.getOne_gold_low();a1=analysis.getOne_gold_avg();
                    ment1="이미 금상이네요!!\n축하합니다!!";
                }
                else if(cc1>=analysis.getOne_silver_low()&&cc1<analysis.getOne_gold_low()){
                    m1="은상";
                    i1=3;
                    b1=analysis.getOne_silver_top();w1=analysis.getOne_silver_low();a1=analysis.getOne_silver_avg();
                    p1=analysis.getOne_gold_low()-cc1;
                    ment1="금상으로 올라가려면\n최소 "+p1+"cm 늘려야 합니다";
                }
                else if(cc1>=analysis.getOne_bronze_low()&&cc1<analysis.getOne_silver_low()){
                    m1="동상";
                    i1=2;
                    b1=analysis.getOne_bronze_top();w1=analysis.getOne_bronze_low();a1=analysis.getOne_bronze_avg();
                    p1=analysis.getOne_silver_low()-cc1;
                    ment1="은상으로 올라가려면\n최소 "+p1+"cm 늘려야 합니다";
                }
                else{
                    m1="참가상";
                    i1=1;
                    b1=analysis.getOne_attend_top();w1=analysis.getOne_attend_low();a1=analysis.getOne_attend_avg();
                    p1=analysis.getOne_bronze_low()-cc1;
                    ment1="동상으로 올라가려면\n최소 "+p1+"cm 늘려야 합니다";
                }

                if(cc2>=analysis.getTwo_gold_low()){
                    m2="금상";
                    i2=4;
                    b2=analysis.getTwo_gold_top();w2=analysis.getTwo_gold_low();a2=analysis.getTwo_gold_avg();
                    ment2="이미 금상이네요!!\n축하합니다!!";
                }
                else if(cc2>=analysis.getTwo_silver_low()&&cc2<analysis.getTwo_gold_low()){
                    m2="은상";
                    i2=3;
                    b2=analysis.getTwo_silver_top();w2=analysis.getTwo_silver_low();a2=analysis.getTwo_silver_avg();
                    p2=analysis.getTwo_gold_low()-cc2;
                    ment2="금상으로 올라가려면\n최소 "+p2+"개 늘려야 합니다";
                }
                else if(cc2>=analysis.getTwo_bronze_low()&&cc2<analysis.getTwo_silver_low()){
                    m2="동상";
                    i2=2;
                    b2=analysis.getTwo_bronze_top();w2=analysis.getTwo_bronze_low();a2=analysis.getTwo_bronze_avg();
                    p2=analysis.getTwo_silver_low()-cc2;
                    ment2="은상으로 올라가려면\n최소 "+p2+"개 늘려야 합니다";
                }
                else{
                    m2="참가상";
                    i2=1;
                    b2=analysis.getTwo_attend_top();w2=analysis.getTwo_attend_low();a2=analysis.getTwo_attend_avg();
                    p2=analysis.getTwo_bronze_low()-cc2;
                    ment2="동상으로 올라가려면\n최소 "+p2+"개 늘려야 합니다";
                }

                if(cc3>=analysis.getThree_gold_low()){
                    m3="금상";
                    i3=4;
                    b3=analysis.getThree_gold_top();w3=analysis.getThree_gold_low();a3=analysis.getThree_gold_avg();
                    ment3="이미 금상이네요!!\n축하합니다!!";
                }
                else if(cc3>=analysis.getThree_silver_low()&&cc3<analysis.getThree_gold_low()){
                    m3="은상";
                    i3=3;
                    b3=analysis.getThree_silver_top();w3=analysis.getThree_silver_low();a3=analysis.getThree_silver_avg();
                    p3=analysis.getThree_gold_low()-cc3;
                    ment3="금상으로 올라가려면\n최소 "+p3+"개 늘려야 합니다";
                }
                else if(cc3>=analysis.getThree_bronze_low()&&cc3<analysis.getThree_silver_low()){
                    m3="동상";
                    i3=2;
                    b3=analysis.getThree_bronze_top();w3=analysis.getThree_bronze_low();a3=analysis.getThree_bronze_avg();
                    p3=analysis.getThree_silver_low()-cc3;
                    ment3="은상으로 올라가려면\n최소 "+p3+"개 늘려야 합니다";
                }
                else{
                    m3="참가상";
                    i3=1;
                    b3=analysis.getThree_attend_top();w3=analysis.getThree_attend_low();a3=analysis.getThree_attend_avg();
                    p3=analysis.getThree_bronze_low()-cc3;
                    ment3="동상으로 올라가려면\n최소 "+p3+"개 늘려야 합니다";
                }

                if(cc4<=analysis.getFour_attend_top()){
                    m4="금상";
                    i4=4;
                    b4=analysis.getFour_attend_low();w4=analysis.getFour_attend_top();a4=analysis.getFour_attend_avg();
                    ment4="이미 금상이네요!!\n축하합니다!!";
                }
                else if(cc4<=analysis.getFour_bronze_top()&&cc4>analysis.getFour_attend_top()){
                    m4="은상";
                    i4=3;
                    b4=analysis.getFour_bronze_low();w4=analysis.getFour_bronze_top();a4=analysis.getFour_bronze_avg();
                    p4=cc4-analysis.getFour_attend_top();
                    ment4="금상으로 올라가려면\n최소 "+p4+"초 줄여야 합니다";
                }
                else if(cc4<=analysis.getFour_silver_top()&&cc4>analysis.getFour_bronze_top()){
                    m4="동상";
                    i4=2;
                    b4=analysis.getFour_silver_low();w4=analysis.getFour_silver_top();a4=analysis.getFour_silver_avg();
                    p4=cc4-analysis.getFour_bronze_top();
                    ment4="은상으로 올라가려면\n최소 "+p4+"초 줄여야 합니다";
                }
                else{
                    m4="참가상";
                    i4=1;
                    b4=analysis.getFour_gold_low();w4=analysis.getFour_gold_top();a4=analysis.getFour_gold_avg();
                    p4=cc4-analysis.getFour_silver_top();
                    ment4="동상으로 올라가려면\n최소 "+p4+"초 줄여야 합니다";
                }

                if(cc5>=analysis.getFive_gold_low()){
                    m5="금상";
                    i5=4;
                    b5=analysis.getFive_gold_top();w5=analysis.getFive_gold_low();a5=analysis.getFive_gold_avg();
                    ment5="이미 금상이네요!!\n축하합니다!!";
                }
                else if(cc5>=analysis.getFive_silver_low()&&cc5<analysis.getFive_gold_low()){
                    m5="은상";
                    i5=3;
                    b5=analysis.getFive_silver_top();w5=analysis.getFive_silver_low();a5=analysis.getFive_silver_avg();
                    p5=analysis.getFive_gold_low()-cc5;
                    ment5="금상으로 올라가려면\n최소 "+p5+"cm 늘려야 합니다";
                }
                else if(cc5>=analysis.getFive_bronze_low()&&cc5<analysis.getFive_silver_low()){
                    m5="동상";
                    i5=2;
                    b5=analysis.getFive_bronze_top();w5=analysis.getFive_bronze_low();a5=analysis.getFive_bronze_avg();
                    p5=analysis.getFive_silver_low()-cc5;
                    ment5="은상으로 올라가려면\n최소 "+p5+"cm 늘려야 합니다";
                }
                else{
                    m5="참가상";
                    i5=1;
                    b5=analysis.getFive_attend_top();w5=analysis.getFive_attend_low();a5=analysis.getFive_attend_avg();
                    p5=analysis.getFive_bronze_low()-cc5;
                    ment5="동상으로 올라가려면\n최소 "+p5+"cm 늘려야 합니다";
                }
                if(i1>=4&&i3>=4&&i5>=4&&(i2>=4||i4>=4)){
                    total_medal="금상";
                }
                else{
                    if(i1>=3&&i3>=3&&i5>=3&&(i2>=3||i4>=3)){
                        total_medal="은상";
                    }
                    else{
                        if(i1>=2&&i3>=2&&i5>=2){
                            total_medal="동상";
                        }
                        else{
                            total_medal="참가상";
                        }
                    }
                }
                ch1.setText("");
                ch2.setText("");
                ch3.setText("");
                ch4.setText("");
                ch5.setText("");
                c1_rank.setText(m1);
                c2_rank.setText(m2);
                c3_rank.setText(m3);
                c4_rank.setText(m4);
                c5_rank.setText(m5);
                after_check.setVisibility(View.VISIBLE);
                after_check_layout.setVisibility(View.VISIBLE);
                check_btn.setVisibility(View.GONE);
                total_rank.setText("종합 "+total_medal+"입니다!");

                check_dialog.dismiss();
            }
        });
    }

    public void setting_result_popup(){
        resultcheck=(LinearLayout)View.inflate(fr4_context, R.layout.result_popup, null);
        check_name=resultcheck.findViewById(R.id.check_name);
        each_medal=resultcheck.findViewById(R.id.each_medal);
        rank_best=resultcheck.findViewById(R.id.rank_best);
        best=resultcheck.findViewById(R.id.best);
        rank_worst=resultcheck.findViewById(R.id.rank_worst);
        worst=resultcheck.findViewById(R.id.worst);
        rank_avg=resultcheck.findViewById(R.id.rank_avg);
        avg=resultcheck.findViewById(R.id.avg);
        advice=resultcheck.findViewById(R.id.advice);
    }

    public void setting_result_btn(){
        check_c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(fr4_context);
                if(resultcheck.getParent()!=null){
                    ((ViewGroup)resultcheck.getParent()).removeView(resultcheck);
                }
                check_name.setText("앉아 윗몸 앞으로 굽히기");
                each_medal.setText("현재 사용자는 "+m1);
                rank_best.setText(m1+" 최고기록");
                rank_worst.setText(m1+" 최저기록");
                rank_avg.setText(m1+" 평균기록");
                best.setText(b1+"cm");
                worst.setText(w1+"cm");
                avg.setText(a1+"cm");
                advice.setText(ment1);
                b.setView(resultcheck);
                result_dialog=b.create();
                result_dialog.show();
            }
        });
        check_c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(fr4_context);
                if(resultcheck.getParent()!=null){
                    ((ViewGroup)resultcheck.getParent()).removeView(resultcheck);
                }
                check_name.setText("교차 윗몸 일으키기");
                each_medal.setText("현재 사용자는 "+m2);
                rank_best.setText(m2+" 최고기록");
                rank_worst.setText(m2+" 최저기록");
                rank_avg.setText(m2+" 평균기록");
                best.setText(b2+"개");
                worst.setText(w2+"개");
                avg.setText(a2+"개");
                advice.setText(ment2);
                b.setView(resultcheck);
                result_dialog=b.create();
                result_dialog.show();
            }
        });
        check_c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(fr4_context);
                if(resultcheck.getParent()!=null){
                    ((ViewGroup)resultcheck.getParent()).removeView(resultcheck);
                }
                check_name.setText("왕복 오래달리기");
                each_medal.setText("현재 사용자는 "+m3);
                rank_best.setText(m3+" 최고기록");
                rank_worst.setText(m3+" 최저기록");
                rank_avg.setText(m3+" 평균기록");
                best.setText(b3+"개");
                worst.setText(w3+"개");
                avg.setText(a3+"개");
                advice.setText(ment3);
                b.setView(resultcheck);
                result_dialog=b.create();
                result_dialog.show();
            }
        });
        check_c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(fr4_context);
                if(resultcheck.getParent()!=null){
                    ((ViewGroup)resultcheck.getParent()).removeView(resultcheck);
                }
                check_name.setText("왕복달리기");
                each_medal.setText("현재 사용자는 "+m4);
                rank_best.setText(m4+" 최고기록");
                rank_worst.setText(m4+" 최저기록");
                rank_avg.setText(m4+" 평균기록");
                best.setText(b4+"초");
                worst.setText(w4+"초");
                avg.setText(a4+"초");
                advice.setText(ment4);
                b.setView(resultcheck);
                result_dialog=b.create();
                result_dialog.show();
            }
        });
        check_c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(fr4_context);
                if(resultcheck.getParent()!=null){
                    ((ViewGroup)resultcheck.getParent()).removeView(resultcheck);
                }
                check_name.setText("제자리 멀리뛰기");
                each_medal.setText("현재 사용자는 "+m5);
                rank_best.setText(m5+" 최고기록");
                rank_worst.setText(m5+" 최저기록");
                rank_avg.setText(m5+" 평균기록");
                best.setText(b5+"cm");
                worst.setText(w5+"cm");
                avg.setText(a5+"cm");
                advice.setText(ment5);
                b.setView(resultcheck);
                result_dialog=b.create();
                result_dialog.show();
            }
        });
    }
}
