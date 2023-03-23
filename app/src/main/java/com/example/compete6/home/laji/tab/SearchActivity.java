package com.example.compete6.home.laji.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.LajiFenAdapter;
import com.example.compete6.adapter.ZhiyuanBannerAdapter;
import com.example.compete6.bean.SearchHotBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.home.laji.LajiActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyGridView;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerSearch;
    private SearchView searchFenlei;
    private MyGridView mygridviewHot;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private LajiFenAdapter lajiFenAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        initView();
        initOther();
        if (token == null) {
            Toast.makeText(SearchActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
        initBanner();
        searchFenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(SearchActivity.this,ResultActivity.class);
                intent1.putExtra("token",token);
                startActivity(intent1);
            }
        });
    }

    private void initBanner() {
        Call<ResponseBody> call=httpbinServices.getSearchBanner(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZhiyuanBannerBean.DataBean> dataBeanLis=JsonParse.getmInstance().getZyBannerList(res);
                    bannerSearch.setImages(dataBeanLis);
                    bannerSearch.setImageLoader(new ZhiyuanBannerAdapter());
                    bannerSearch.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getSearchHot(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<SearchHotBean.DataBean> dataBeanList= JsonParse.getmInstance().getSearchHot(res);
                    lajiFenAdapter.setRowsBeanList(dataBeanList);
                    lajiFenAdapter.setToken(token);
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
                Intent intent = new Intent(SearchActivity.this, LajiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("搜索页");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        bannerSearch = findViewById(R.id.banner_search);
        searchFenlei = findViewById(R.id.search_fenlei);
        mygridviewHot = findViewById(R.id.mygridview_hot);
        lajiFenAdapter=new LajiFenAdapter(this);
        mygridviewHot.setAdapter(lajiFenAdapter);
    }
}