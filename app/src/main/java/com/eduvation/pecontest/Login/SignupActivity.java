package com.eduvation.pecontest.Login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Class.Register;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    RetrofitAPI myAPI;
    EditText register_email, register_pwd, register_repwd, register_nickname;
    Spinner register_age, register_location;
    Button register;
    String email=null, pw1=null, pw2=null, nickname=null, age="나이 선택", location="지역 선택";
    int loc_num;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        myAPI= RetrofitClient.getApiService();

        setting_view();
        setting_register_button();
    }

    public void setting_view(){
        register_email=findViewById(R.id.register_email);
        register_pwd=findViewById(R.id.register_pwd);
        register_repwd=findViewById(R.id.register_repwd);
        register_nickname=findViewById(R.id.register_nickname);
        register_age=findViewById(R.id.register_age);
        register_location=findViewById(R.id.register_location);
        register=findViewById(R.id.register);
    }

    public void setting_register_button(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=register_email.getText().toString().trim();
                pw1=register_pwd.getText().toString().trim();
                pw2=register_repwd.getText().toString().trim();
                nickname=register_nickname.getText().toString().trim();
                age=register_age.getSelectedItem().toString().trim();
                location=register_location.getSelectedItem().toString().trim();
                if(TextUtils.isEmpty(email)||email==null||email==""||email==" "){
                    Toast.makeText(SignupActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pw1)||pw1==null||pw1==""||pw1==" "){
                    Toast.makeText(SignupActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pw2)||pw2==null||pw2==""||pw2==" "){
                    Toast.makeText(SignupActivity.this, "비밀번호를 재입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(nickname)||nickname==null||nickname==""||nickname==" "){
                    Toast.makeText(SignupActivity.this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(age.equals("나이 선택")){
                    Toast.makeText(SignupActivity.this, "나이를 선택해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(location.equals("지역 선택")){
                    Toast.makeText(SignupActivity.this, "지역을 선택해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch(location){
                    case "서울": loc_num=0;break;
                    case "대구": loc_num=1;break;
                    case "대전": loc_num=2;break;
                    case "광주": loc_num=3;break;
                    case "인천": loc_num=4;break;
                    case "부산": loc_num=5;break;
                    case "울산": loc_num=6;break;
                    case "세종": loc_num=7;break;
                    case "제주": loc_num=8;break;
                    case "경기": loc_num=9;break;
                    case "강원": loc_num=10;break;
                    case "충남": loc_num=11;break;
                    case "충북": loc_num=12;break;
                    case "전남": loc_num=13;break;
                    case "전북": loc_num=14;break;
                    case "경남": loc_num=15;break;
                    case "경북": loc_num=16;break;
                }
                Register now_register=new Register(email, pw1, pw2, nickname, age, loc_num);
                Call<Void> register=myAPI.signup(now_register);
                register.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "회원가입 Fail", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            }
        });

    }
}
