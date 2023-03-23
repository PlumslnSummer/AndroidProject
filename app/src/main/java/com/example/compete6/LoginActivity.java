package com.example.compete6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.bean.LoginBean;
import com.example.compete6.bean.SuccessBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etUserpwd;
    private Button btnLogin;
    private Button btnIntohome;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Intent intent = null;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        initView();
        initData();
    }

    private void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUsername.getText().toString();
                String pwd = etUserpwd.getText().toString();
                LoginBean loginBean = new LoginBean();
                loginBean.setUsername(name);
                loginBean.setPassword(pwd);
                Call<SuccessBean> call = httpbinServices.postLogin(loginBean);
                call.enqueue(new Callback<SuccessBean>() {
                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                        Toast.makeText(LoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        if (response.body().getCode() == 200) {
                            token = response.body().getToken();
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("token", token);
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(Call<SuccessBean> call, Throwable throwable) {

                    }
                });

            }
        });
        btnIntohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etUserpwd = (EditText) findViewById(R.id.et_userpwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnIntohome = (Button) findViewById(R.id.btn_intohome);
        btnRegister = findViewById(R.id.btn_register);
    }
}