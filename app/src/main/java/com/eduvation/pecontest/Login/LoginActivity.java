package com.eduvation.pecontest.Login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eduvation.pecontest.Class.Login;
import com.eduvation.pecontest.Class.User;
import com.eduvation.pecontest.Network.RetrofitAPI;
import com.eduvation.pecontest.Network.RetrofitClient;
import com.eduvation.pecontest.R;

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
                login.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            User now_user=response.body();
                            login_check.setText(now_user.getKey()+"\n"+now_user.getEmail()+"\n"+now_user.getPk());
                            return;
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        login_check.setText("이메일/비밀번호를 확인해주세요");
                        login_pwd.setText("");
                        email=null;
                        password=null;
                        return;
                    }
                });
            }
        });
    }
}
