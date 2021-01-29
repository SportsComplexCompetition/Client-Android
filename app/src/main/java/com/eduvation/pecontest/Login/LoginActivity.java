package com.eduvation.pecontest.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Activity.MainActivity;
import com.eduvation.pecontest.Class.Login;
import com.eduvation.pecontest.Class.Login_User;
import com.eduvation.pecontest.Class.User;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    RetrofitAPI myAPI;
    EditText login_email, login_pwd;
    Button login;
    TextView signup, login_check;
    String email=null, password=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        myAPI= RetrofitClient.getApiService();

        setting_view();
        setting_loginbutton();
        setting_signupbutton();
    }

    public void setting_view(){
        login_email=findViewById(R.id.login_email);
        login_pwd=findViewById(R.id.login_pwd);
        login=findViewById(R.id.login_btn);
        signup=findViewById(R.id.to_register);
        login_check=findViewById(R.id.login_check);
    }

    public void setting_loginbutton(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=login_email.getText().toString().trim();
                password=login_pwd.getText().toString().trim();
                if(TextUtils.isEmpty(email)||email==null||email==""||email==" "){
                    Toast.makeText(LoginActivity.this, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)||password==null||password==""||password==" "){
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                Login now_login=new Login(email, password);
                Call<User> login=myAPI.login(now_login);
                System.out.println(email+"---");
                System.out.println(password+"---");
                login.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            User now_user=response.body();
                            System.out.println("1차 로그인 성공");
                            //token shape is "Token ab~~~"
                            Call<Login_User> get_login=myAPI.get_login_user("Token "+now_user.getKey(), now_user.getPk());
                            get_login.enqueue(new Callback<Login_User>() {
                                @Override
                                public void onResponse(Call<Login_User> call, Response<Login_User> response) {
                                    if(response.isSuccessful()){
                                        now_user.setLocation(response.body().getLocation());
                                        now_user.setNickname(response.body().getNickname());
                                        finish();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    }
                                    else{
                                        System.out.println(response);
                                        set_login_statemessage(1);
                                        return;
                                    }
                                }
                                @Override
                                public void onFailure(Call<Login_User> call, Throwable t) {
                                    set_login_statemessage(2);
                                    System.out.println(t);
                                    return;
                                }
                            });
                            return;
                        }
                        set_login_statemessage(1);
                        return;
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        set_login_statemessage(2);
                        return;
                    }
                });
            }
        });
    }

    public void setting_signupbutton(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public void set_login_statemessage(int choice){
        switch(choice){
            case 1:
                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                login_check.setVisibility(View.VISIBLE);
                login_check.setText("이메일/비밀번호를 확인해주세요");
                login_pwd.setText("");
                email=null;
                password=null;
                break;
            case 2:
                login_check.setVisibility(View.VISIBLE);
                login_check.setText("서버 통신 불가");
                login_pwd.setText("");
                email=null;
                password=null;
                break;
        }
    }
}
