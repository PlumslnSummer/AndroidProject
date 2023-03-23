package com.example.compete6.home.laji;

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
import com.example.compete6.adapter.LajiXinwenAdapter;
import com.example.compete6.adapter.ZhiyuanBannerAdapter;
import com.example.compete6.bean.LajiXwListBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.home.laji.tab.FenleiActivity;
import com.example.compete6.home.laji.tab.SearchActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
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

public class LajiActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerLaji;
    private Button btnSearch;
    private Button btnFenlei;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private LajiXinwenAdapter lajiXinwenAdapter;
    private Integer id;
    private MyListView mylistviewLaji;
    private TextView tvXin;
    private TextView tvDang;
    private TextView tvFenlei;
    private TextView tvShequ,tv_current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laji);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        id=7;
        initView();
        initOther();
        if (token == null) {
            Toast.makeText(LajiActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initBanner();
        initData();
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LajiActivity.this, SearchActivity.class);
                intent1.putExtra("token",token);
                startActivity(intent1);
            }
        });
        btnFenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(LajiActivity.this, FenleiActivity.class);
                intent1.putExtra("token",token);
                startActivity(intent1);
            }
        });
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getLajiXinwenList(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<LajiXwListBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getLajiXwList(res);
                    lajiXinwenAdapter.setRowsBeanList(rowsBeanList);
                    lajiXinwenAdapter.setToken(token);
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
        Call<ResponseBody> call = httpbinServices.getLajiBanner(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<ZhiyuanBannerBean.DataBean> dataBeanList = JsonParse.getmInstance().getZyBannerList(res);
                    bannerLaji.setImages(dataBeanList);
                    bannerLaji.setImageLoader(new ZhiyuanBannerAdapter());
                    bannerLaji.start();
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
                Intent intent = new Intent(LajiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("垃圾分类");
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        bannerLaji = findViewById(R.id.banner_laji);
        btnSearch = findViewById(R.id.btn_search);
        btnFenlei = findViewById(R.id.btn_fenlei);
        lajiXinwenAdapter = new LajiXinwenAdapter(this);
        mylistviewLaji = findViewById(R.id.mylistview_laji);
        mylistviewLaji.setAdapter(lajiXinwenAdapter);
        tvXin = findViewById(R.id.tv_xin);
        tvDang = findViewById(R.id.tv_dang);
        tvFenlei = findViewById(R.id.tv_fenlei);
        tvShequ = findViewById(R.id.tv_shequ);
        tvXin.setOnClickListener(this);
        tvDang.setOnClickListener(this);
        tvFenlei.setOnClickListener(this);
        tvShequ.setOnClickListener(this);

        tv_current=tvXin;
        tvXin.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        tv_current.setSelected(false);
        switch (v.getId()){
            case R.id.tv_xin:
                id=7;
                tv_current=tvXin;
                tvXin.setSelected(true);
                break;
            case R.id.tv_dang:
                id=8;
                tv_current=tvDang;
                tvDang.setSelected(true);
                break;
            case R.id.tv_fenlei:
                id=9;
                tv_current=tvFenlei;
                tvFenlei.setSelected(true);
                break;
            case R.id.tv_shequ:
                id=10;
                tv_current=tvShequ;
                tvShequ.setSelected(true);
                break;
        }
        initData();
    }
}