package com.example.compete6.home.metrotab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.StationBean;
import com.example.compete6.home.metrotab.MetroActivity;
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

public class AllLinesActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivAll;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lines);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);

        initView();
        initOther();
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        if(token==null){
            Toast.makeText(AllLinesActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getAllStstion(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    StationBean.DataBean dataBean= JsonParse.getmInstance().getStation(res);
                    Glide.with(AllLinesActivity.this)
                            .load(Contants.WEB_URL+dataBean.getImgUrl())
                            .error(R.mipmap.ic_launcher)
                            .into(ivAll);
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
                Intent intent = new Intent(AllLinesActivity.this, MetroActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("地铁站总览路线图");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivAll = (ImageView) findViewById(R.id.iv_all);
    }
}