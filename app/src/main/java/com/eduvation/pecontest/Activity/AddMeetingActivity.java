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

import com.eduvation.pecontest.Class.Communication;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMeetingActivity extends AppCompatActivity {
    EditText meeting_title, meeting_body, meeting_address;
    Spinner meeting_loc, meeting_pe;
    TextView make_btn;
    String title="", body="", address="";
    String loc="지역", pe="종목";
    int location=0;
    Context meeting_context;

    RetrofitAPI myAPI;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_meeting);

        setting_view();
        setting_spinner();
        setting_clicklistener();
    }

    public void setting_view(){
        meeting_title=findViewById(R.id.meeting_title);
        meeting_body=findViewById(R.id.meeting_body);
        meeting_address=findViewById(R.id.meeting_address);
        meeting_loc=findViewById(R.id.meeting_loc);
        meeting_pe=findViewById(R.id.meeting_pe);
        make_btn=findViewById(R.id.make_btn);
        meeting_context=this;

    }
    public void setting_spinner(){
        meeting_loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loc=meeting_loc.getSelectedItem().toString();
                location=meeting_loc.getSelectedItemPosition();
                //Toast.makeText(meeting_context, loc+""+location, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        meeting_pe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pe=meeting_pe.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setting_clicklistener(){
        make_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make_meeting();
            }
        });
    }
    public void make_meeting(){
        title=meeting_title.getText().toString().trim();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(meeting_context, "소모임 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(loc.equals("지역")){
            Toast.makeText(meeting_context, "지역을 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pe.equals("종목")){
            Toast.makeText(meeting_context, "종목을 선택해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        body=meeting_body.getText().toString().trim();
        if(TextUtils.isEmpty(body)){
            Toast.makeText(meeting_context, "소모임 설명을 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        address=meeting_address.getText().toString().trim();
        if(TextUtils.isEmpty(address)){
            Toast.makeText(meeting_context, "연락처를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        myAPI= RetrofitClient.getApiService();
        Date date=new Date();
        Communication newcommute=new Communication(7, "woojun", 6, location-1, title, 3, body, date, pe, address);
        Call<Void> newmeeting=myAPI.make_communication(newcommute);
        newmeeting.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("새 소모임 개설 성공");
                ((MainActivity)MainActivity.main_context).fragment2.adapter.addnewItem(newcommute);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("새 소모임 개설 실패");
            }
        });
        finish();
    }
}
