package com.example.compete6.home.moneytab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.JiaofeiBean;
import com.example.compete6.bean.PhoneJiaofeiBean;
import com.example.compete6.home.metrotab.MetroActivity;
import com.example.compete6.home.moneytab.MoneyTabActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JiaofeiActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvPhone;
    private TextView tv50;
    private TextView tv100;
    private TextView tv200,tv_current;
    private Button btnZhifu;
    private String phone;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiaofei);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        token=intent.getStringExtra("token");
        initView();
        initOther();
        if (token == null) {
            Toast.makeText(JiaofeiActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        tvPhone.setText(phone);
        btnZhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JiaofeiBean jiaofeiBean=new JiaofeiBean();
                jiaofeiBean.setPaymentType("电子支付");
                jiaofeiBean.setPhonenumber(phone);
                jiaofeiBean.setRechargeAmount(money);
                jiaofeiBean.setRuleId(1);
                jiaofeiBean.setType("2");
                Call<PhoneJiaofeiBean> call=httpbinServices.postjiaofei(token,jiaofeiBean);
                call.enqueue(new Callback<PhoneJiaofeiBean>() {
                    @Override
                    public void onResponse(Call<PhoneJiaofeiBean> call, Response<PhoneJiaofeiBean> response) {
                        Toast.makeText(JiaofeiActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PhoneJiaofeiBean> call, Throwable throwable) {

                    }
                });
            }
        });

    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JiaofeiActivity.this, MoneyTabActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("交费页面");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        tv50 = (TextView) findViewById(R.id.tv_50);
        tv100 = (TextView) findViewById(R.id.tv_100);
        tv200 = (TextView) findViewById(R.id.tv_200);
        btnZhifu = (Button) findViewById(R.id.btn_zhifu);

        tv50.setOnClickListener(this);
        tv100.setOnClickListener(this);
        tv200.setOnClickListener(this);
        tv_current=tv50;
        tv50.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        tv_current.setSelected(false);
        switch (v.getId()){
            case R.id.tv_50:
                money=50;
                tv_current=tv50;
                tv50.setSelected(true);
                break;
            case R.id.tv_100:
                money=100;
                tv_current=tv100;
                tv100.setSelected(true);
                break;
            case R.id.tv_200:
                money=200;
                tv_current=tv200;
                tv200.setSelected(true);
                break;
        }
    }
}