package com.eduvation.pecontest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    RetrofitAPI myAPI;

    Button make_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_meeting);

        make_btn=findViewById(R.id.make_btn);
        make_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make_meeting();
            }
        });
    }

    public void make_meeting(){
        myAPI= RetrofitClient.getApiService();
        Date date=new Date();
        Communication newcommute=new Communication(3, 1, "두번째 테스트입니다", 3, "이것은 바로바로 테스트", date, "점프점프", "kakao 비밀");
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
