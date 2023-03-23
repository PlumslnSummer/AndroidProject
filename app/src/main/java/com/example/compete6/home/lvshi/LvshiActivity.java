package com.example.compete6.home.lvshi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.FlzcAdapter;
import com.example.compete6.adapter.LvshiTopListAdapter;
import com.example.compete6.adapter.ZhiyuanBannerAdapter;
import com.example.compete6.bean.FlzcBean;
import com.example.compete6.bean.LvshiTopListBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.home.lvshi.tab.LvshiListActivity;
import com.example.compete6.home.lvshi.tab.zixun.ZixunActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyGridView;
import com.example.compete6.util.MyListView;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LvshiActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerLvshi;
    private MyGridView mygridviewZc;
    private LinearLayout linearZixun;
    private LinearLayout linearTheme;
    private MyListView mylistviewLvshi;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private FlzcAdapter flzcAdapter;
    private LvshiTopListAdapter lvshiTopListAdapter;
    private TextView tvCheckmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvshi);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        initData();
        initBanner();
        tvCheckmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LvshiActivity.this, LvshiListActivity.class);
                intent1.putExtra("token",token);
                startActivity(intent1);
            }
        });
        linearZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LvshiActivity.this, ZixunActivity.class);
                intent1.putExtra("token",token);
                startActivity(intent1);
            }
        });
    }

    private void initBanner() {
        Call<ResponseBody> call = httpbinServices.getLvshiBanner(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<ZhiyuanBannerBean.DataBean> dataBeanList = JsonParse.getmInstance().getZyBannerList(res);
                    bannerLvshi.setImages(dataBeanList);
                    bannerLvshi.setImageLoader(new ZhiyuanBannerAdapter());
                    bannerLvshi.start();
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
        Call<ResponseBody> call = httpbinServices.getLvshiZcList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<FlzcBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getZcList(res);
                    flzcAdapter.setRowsBeanList(rowsBeanList);
                    flzcAdapter.setToken(token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        Call<ResponseBody> call1 = httpbinServices.getHplTopList(token);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<LvshiTopListBean.DataBean> dataBeanList = JsonParse.getmInstance().getTopList(res);
                    lvshiTopListAdapter.setDataBeanList(dataBeanList);
                    lvshiTopListAdapter.setToken(token);
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
                Intent intent = new Intent(LvshiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("法律咨询");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        bannerLvshi = (Banner) findViewById(R.id.banner_lvshi);
        mygridviewZc = (MyGridView) findViewById(R.id.mygridview_zc);
        linearZixun = (LinearLayout) findViewById(R.id.linear_zixun);
        linearTheme = (LinearLayout) findViewById(R.id.linear_theme);
        mylistviewLvshi = (MyListView) findViewById(R.id.mylistview_lvshi);
        flzcAdapter = new FlzcAdapter(this);
        mygridviewZc.setAdapter(flzcAdapter);
        lvshiTopListAdapter = new LvshiTopListAdapter(this);
        mylistviewLvshi.setAdapter(lvshiTopListAdapter);
        tvCheckmore = (TextView) findViewById(R.id.tv_checkmore);
    }
}