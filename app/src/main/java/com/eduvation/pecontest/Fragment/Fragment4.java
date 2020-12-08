package com.eduvation.pecontest.Fragment;

import android.app.AlertDialog;
import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.eduvation.pecontest.Adapter.MyMatchAdapter;
import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Class.User;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageCompetition;
import com.eduvation.pecontest.Singleton.ManageUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment4 extends Fragment {
    TextView my_name, my_age, my_email, my_gender;
    Button check_btn;
    ImageView no_match_img;
    RecyclerView my_match;
    String nickname="", email="";
    int location=-1;
    String loc="";
    Context fr4_context;

    ArrayList<Integer> img=null;
    ArrayList<Competition> my_comp=null;
    public MyMatchAdapter myadapter=null;
    int mypk=6;
    User my=null;
    RetrofitAPI myAPI;

    AlertDialog check_dialog;
    LinearLayout check;
    EditText ch1, ch2, ch3, ch4, ch5;
    TextView submit;
    String c1="", c2="", c3="", c4="", c5="";
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
        fr4_context=getContext();

        getting_myinfo();
        setting_recyclerview();
        setting_check_popup();
        setting_check_btn();


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
    }
    public void setting_recyclerview(){
        ArrayList<Competition> total=new ArrayList<>();
        total= ManageCompetition.getInstance().getMatch_total();
        img=new ArrayList<>();
        my_comp=new ArrayList<>();
        img.add(R.drawable.match1);img.add(R.drawable.match2);img.add(R.drawable.match3);img.add(R.drawable.match4);
        for(int i=0; i<total.size(); i++){
            if(mypk==total.get(i).getHost()){
                my_comp.add(total.get(i));
            }
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
                ch1.setText("");
                ch2.setText("");
                ch3.setText("");
                ch4.setText("");
                ch5.setText("");
                check_dialog.dismiss();
                Toast.makeText(fr4_context, c1+" "+c2+" "+c3+" "+c4+" "+c5, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
