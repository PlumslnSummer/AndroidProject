package com.example.compete6.home.wuliutab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.ZhiyuanBannerAdapter;
import com.example.compete6.bean.WuliuDetailBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.home.wuliutab.WuliuActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WuliudetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerWuliuDe;
    private TextView tvCompanyIntro;
    private TextView tvCompanyShip;
    private TextView tvCompanyPrice;
    private WuliuDetailBean.DataBean dataBean;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliudetail);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        id=intent.getIntExtra("id",-1);
        initBanner();
        initData();

    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getWuliuDetail(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean=JsonParse.getmInstance().getWuliu(res);
                    tvCompanyIntro.setText("公司简介："+dataBean.getIntroduce());
                    tvCompanyShip.setText("运输方式介绍："+dataBean.getShippingMethod());
                    tvCompanyPrice.setText("运费介绍信息：");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initBanner() {
        Call<ResponseBody> call=httpbinServices.getWUliuBannerList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZhiyuanBannerBean.DataBean> dataBeanList= JsonParse.getmInstance().getZyBannerList(res);
                    bannerWuliuDe.setImages(dataBeanList);
                    bannerWuliuDe.setImageLoader(new ZhiyuanBannerAdapter());
                    bannerWuliuDe.start();
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
                Intent intent=new Intent(WuliudetailActivity.this, WuliuActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("物流公司详情页面");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        bannerWuliuDe = (Banner) findViewById(R.id.banner_wuliu_de);
        tvCompanyIntro = (TextView) findViewById(R.id.tv_company_intro);
        tvCompanyShip = (TextView) findViewById(R.id.tv_company_ship);
        tvCompanyPrice = (TextView) findViewById(R.id.tv_company_price);
    }
}