package com.example.compete6.per.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.PwdBean;
import com.example.compete6.bean.SuccessBean;
import com.example.compete6.bean.UserInforBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerPwdActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private EditText etOldpwd;
    private EditText etNewpwd;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_pwd);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        initData();
    }

    private void initData() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldpwd=etOldpwd.getText().toString();
                String newpwd=etNewpwd.getText().toString();
                PwdBean pwdBean=new PwdBean();
                pwdBean.setOldPassword(oldpwd);
                pwdBean.setNewPassword(newpwd);
                Call<SuccessBean> call=httpbinServices.putUserPwd(token,pwdBean);
                call.enqueue(new Callback<SuccessBean>() {
                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                        Toast.makeText(PerPwdActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SuccessBean> call, Throwable throwable) {

                    }
                });
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerPwdActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("修改密码");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        etOldpwd = (EditText) findViewById(R.id.et_oldpwd);
        etNewpwd = (EditText) findViewById(R.id.et_newpwd);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }
}