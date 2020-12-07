package com.eduvation.pecontest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Class.Competition;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMatchActivity extends AppCompatActivity {
    RetrofitAPI myAPI;

    Button make_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmatch);

        make_btn=findViewById(R.id.make_btn);
        make_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                make_match();
            }
        });
    }
    public void make_match(){
        myAPI= RetrofitClient.getApiService();
        Date date=new Date();
        Competition competition=new Competition(-1, 0, "달리기", "", date, date, 10, 0, 1000, null, null);
        Call<Void> newmatch=myAPI.make_competition(competition);
        newmatch.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                ((MainActivity)MainActivity.main_context).fragment3.matchAdapter.addnewItem(competition);
                ((MainActivity)MainActivity.main_context).fragment3.addnewMatch();
                System.out.println("경쟁 개설 성공");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("경쟁 개설 실패");
            }
        });
    }
}
