package com.example.compete6.home.zhengfu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.SuqiuDeBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SuqiuDeActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivSuqiu;
    private TextView tvChengban;
    private TextView tvTime;
    private TextView tvState;
    private TextView tvResult;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suqiu_de);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        id=intent.getIntExtra("id",-1);
        initView();
        initOther();
        if(token==null){
            Toast.makeText(SuqiuDeActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getSuqiuDe(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    SuqiuDeBean.DataBean dataBean= JsonParse.getmInstance().getSuqiude(res);
                    tvTitle.setText("标题："+dataBean.getTitle());
                    tvContent.setText("内容："+dataBean.getContent());
                    Glide.with(SuqiuDeActivity.this)
                            .load(Contants.WEB_URL+dataBean.getImgUrl())
                            .error(R.mipmap.ic_launcher)
                            .into(ivSuqiu);
                    tvChengban.setText("承办单位："+dataBean.getUndertaker());
                    tvTime.setText("提交时间："+dataBean.getCreateTime());
                    tvState.setText("处理状态："+dataBean.getState());
                    tvResult.setText("处理结果："+dataBean.getDetailResult());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SuqiuDeActivity.this, SuqiuListActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("诉求详情");
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        tvContent = findViewById(R.id.tv_content);
        ivSuqiu = findViewById(R.id.iv_suqiu);
        tvChengban = findViewById(R.id.tv_chengban);
        tvTime = findViewById(R.id.tv_time);
        tvState = findViewById(R.id.tv_state);
        tvResult = findViewById(R.id.tv_result);
    }
}