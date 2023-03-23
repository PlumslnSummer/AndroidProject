package com.example.compete6.home.lvshi.tab.zixun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.LvshiDetailBean;
import com.example.compete6.bean.ZixunDeBean;
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

public class ZixunDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivLvshi;
    private TextView tvLvshiname;
    private TextView tvFlzc;
    private TextView tvServicestime;
    private TextView tvFavo;
    private TextView tvState;
    private TextView tvType;
    private TextView tvContent;
    private ImageView ivImg;
    private TextView tvPhone;
    private Button btnPingjia;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;
    private ZixunDeBean.DataBean dataBean;
    private TextView tvGood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixun_detail);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        id = intent.getIntExtra("id", -1);
        initView();
        initOther();
        initData();
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getZIxunDe(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    dataBean = JsonParse.getmInstance().getZIxunde(res);
                    tvLvshiname.setText("律师名称：" + dataBean.getLawyerName());
                    tvState.setText("受理状态："+dataBean.getState());
                    tvContent.setText("问题描述："+dataBean.getContent());
                    tvPhone.setText("联系电话："+dataBean.getPhone());
                    Glide.with(ZixunDetailActivity.this)
                            .load(Contants.WEB_URL+dataBean.getImageUrls())
                            .error(R.mipmap.ic_launcher)
                            .into(ivImg);
                    Call<ResponseBody> call1 = httpbinServices.getLvshidetail(token, dataBean.getLawyerId());
                    call1.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String res = response.body().string().trim();
                                LvshiDetailBean.DataBean dataBean = JsonParse.getmInstance().getLvshiDetail(res);
                                tvFlzc.setText("法律专长：" + dataBean.getLegalExpertiseName());
                                tvServicestime.setText("咨询人数：" + dataBean.getServiceTimes());
                                tvFavo.setText("从业年限：" + dataBean.getWorkStartAt());
                                tvGood.setText("好评率："+dataBean.getFavorableRate());
                                tvType.setText("问题类型："+dataBean.getLegalExpertiseName());
                                Glide.with(ZixunDetailActivity.this)
                                        .load(Contants.WEB_URL+dataBean.getAvatarUrl())
                                        .error(R.drawable.head)
                                        .into(ivLvshi);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        btnPingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZixunDetailActivity.this,FuwuPingjiaActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZixunDetailActivity.this, ZixunActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("咨询详情页");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivLvshi = (ImageView) findViewById(R.id.iv_lvshi);
        tvLvshiname = (TextView) findViewById(R.id.tv_lvshiname);
        tvFlzc = (TextView) findViewById(R.id.tv_flzc);
        tvServicestime = (TextView) findViewById(R.id.tv_servicestime);
        tvFavo = (TextView) findViewById(R.id.tv_favo);
        tvState = (TextView) findViewById(R.id.tv_state);
        tvType = (TextView) findViewById(R.id.tv_type);
        tvContent = (TextView) findViewById(R.id.tv_content);
        ivImg = (ImageView) findViewById(R.id.iv_img);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        btnPingjia = (Button) findViewById(R.id.btn_pingjia);
        tvGood = (TextView) findViewById(R.id.tv_good);
    }
}