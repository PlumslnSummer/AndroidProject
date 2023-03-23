package com.example.compete6.per.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.PutUserInforBean;
import com.example.compete6.bean.SuccessBean;
import com.example.compete6.bean.UserInforBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerInforActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivPerhead;
    private TextView tvUsername;
    private EditText etNickname;
    private RadioButton rbM;
    private RadioButton rbW;
    private EditText etPhone;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private UserInforBean.UserBean userBean;
    private String token;
    private Button btnSubmit;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per_infor);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        userBean = (UserInforBean.UserBean) intent.getSerializableExtra("user");
        if (userBean == null) {
            return;
        }
        iniData();
    }

    private void iniData() {
        Glide.with(this)
                .load(Contants.WEB_URL+userBean.getAvatar())
                .error(R.drawable.head)
                .into(ivPerhead);
        tvUsername.setText(userBean.getUserName());
        etNickname.setText(userBean.getNickName());
        etPhone.setText(userBean.getPhonenumber());
        if(userBean.getSex().equals("0")){
            rbM.setChecked(true);
        }else {
            rbW.setChecked(true);
        }
        rbM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex="0";
            }
        });
        rbW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex="1";
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickename=etNickname.getText().toString();
                String phone=etPhone.getText().toString();
                PutUserInforBean putUserInforBean=new PutUserInforBean();
                putUserInforBean.setNickName(nickename);
                putUserInforBean.setPhonenumber(phone);
                putUserInforBean.setSex(sex);
                Call<SuccessBean> call=httpbinServices.putUserInfior(token,putUserInforBean);
                call.enqueue(new Callback<SuccessBean>() {
                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {
                        Toast.makeText(PerInforActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(PerInforActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("个人信息");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivPerhead = (ImageView) findViewById(R.id.iv_perhead);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        etNickname = (EditText) findViewById(R.id.et_nickname);
        rbM = (RadioButton) findViewById(R.id.rb_M);
        rbW = (RadioButton) findViewById(R.id.rb_W);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }
}