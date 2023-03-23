package com.example.compete6.home.laji.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.FenleiBean;
import com.example.compete6.home.laji.LajiActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FenleiJieshaoActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivFenlei;
    private TextView tvShuom;
    private TextView tvYaoqiiu;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private FenleiBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei_jieshao);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        rowsBean=(FenleiBean.RowsBean)intent.getSerializableExtra("fenlei");

        initView();
        initOther();
        if (rowsBean == null) {
            Toast.makeText(FenleiJieshaoActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Glide.with(this)
                .load(Contants.WEB_URL+rowsBean.getImgUrl())
                .error(R.drawable.kehui)
                .into(ivFenlei);
        tvShuom.setText(rowsBean.getIntroduce());
        tvYaoqiiu.setText(rowsBean.getGuide());
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FenleiJieshaoActivity.this, FenleiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("分类介绍页");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        ivFenlei = findViewById(R.id.iv_fenlei);
        tvShuom = findViewById(R.id.tv_shuom);
        tvYaoqiiu = findViewById(R.id.tv_yaoqiiu);
    }
}