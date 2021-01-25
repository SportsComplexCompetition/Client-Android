package com.eduvation.pecontest.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;
import com.eduvation.pecontest.Singleton.ManageUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMatchActivity extends AppCompatActivity {
    EditText match_title, match_end, match_people, match_money;
    Spinner match_loc, match_pe;
    TextView match_btn;
    String title="", end="", loc="지역", pe="종목";
    int people=-1, money=-1, location=0;
    Context match_context;

    RetrofitAPI myAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmatch);

        setting_view();
        setting_spinner();
        setting_clicklistener();

    }

    public void setting_view(){
        match_title=findViewById(R.id.match_title);
        match_end=findViewById(R.id.match_end);
        match_people=findViewById(R.id.match_people);
        match_money=findViewById(R.id.match_money);
        match_loc=findViewById(R.id.match_loc);
        match_pe=findViewById(R.id.match_pe);
        match_btn=findViewById(R.id.match_make);
        match_context=this;
    }
    public void setting_spinner(){
        match_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc=match_loc.getSelectedItem().toString();
                location=match_loc.getSelectedItemPosition();
                //Toast.makeText(match_context, loc+" "+location, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        match_pe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pe=match_pe.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setting_clicklistener(){
        match_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make_match();
            }
        });
    }
    public void make_match(){
        title=match_title.getText().toString().trim();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(match_context, "경쟁 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(loc.equals("지역")){
            Toast.makeText(match_context, "지역을 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pe.equals("종목")){
            Toast.makeText(match_context, "종목을 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        end=match_end.getText().toString().trim();
        if(TextUtils.isEmpty(end)){
            Toast.makeText(match_context, "마감 날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(match_people.getText().toString().trim())){
            Toast.makeText(match_context, "최대 인원을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        people=Integer.parseInt(match_people.getText().toString().trim());
        if(TextUtils.isEmpty(match_money.getText().toString().trim())){
            Toast.makeText(match_context, "참가비를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        money=Integer.parseInt(match_money.getText().toString().trim());

        myAPI= RetrofitClient.getApiService();
        Date date=new Date();
        ArrayList<Integer> joined=new ArrayList<>();
        joined.add(6);
        Competition competition=new Competition(ManageUser.getInstance().getMe().getNickname(), location-1, -1, 0, pe, title,
                date, end, people, money, money, 6, joined);
        Call<Void> newmatch=myAPI.make_competition(competition);
        newmatch.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    ((MainActivity)MainActivity.main_context).fragment3.matchAdapter.addnewItem(competition);
                    ((MainActivity)MainActivity.main_context).fragment3.addnewMatch();
                    ((MainActivity)MainActivity.main_context).fragment4.myadapter.addnewItem(competition);
                    ((MainActivity)MainActivity.main_context).fragment4.addnewMatch();
                    System.out.println("경쟁 개설 성공");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("경쟁 개설 실패");
            }
        });
    }
}
