package com.example.compete6.home.zhengfu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.ZfFenleiAdapter;
import com.example.compete6.bean.ZfBannerBean;
import com.example.compete6.bean.ZfFenleiBena;
import com.example.compete6.bean.ZhengfuFenBean;
import com.example.compete6.home.qingnian.QingnianActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyGridView;
import com.example.compete6.util.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZhengfuActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerZhengfu;
    private MyGridView gridviewZhengfu;
    private MyListView mylistviewSuqiu;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private ZfFenleiAdapter zfFenleiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhengfu);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        initView();
        initOther();
        if(token==null){
            Toast.makeText(ZhengfuActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initBanner();
        initFenLei();
    }

    private void initFenLei() {
        Call<ResponseBody> call=httpbinServices.getZfFenlei(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZfFenleiBena.RowsBean> rowsBeanList=JsonParse.getmInstance().getZfFenList(res);
                    zfFenleiAdapter.setRowsBeanList(rowsBeanList);
                    zfFenleiAdapter.setToken(token);
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
        Call<ResponseBody> call=httpbinServices.getZfBanner(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZfBannerBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getZfBanner(res);
                    bannerZhengfu.setImages(rowsBeanList);
                    bannerZhengfu.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            Glide.with(context)
                                    .load(Contants.WEB_URL+o)
                                    .error(R.mipmap.ic_launcher)
                                    .into(imageView);
                        }
                    });
                    bannerZhengfu.start();
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
                Intent intent=new Intent(ZhengfuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("政府热线");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        bannerZhengfu = findViewById(R.id.banner_zhengfu);
        gridviewZhengfu = findViewById(R.id.gridview_zhengfu);
        mylistviewSuqiu = findViewById(R.id.mylistview_suqiu);
        zfFenleiAdapter=new ZfFenleiAdapter(this);
        gridviewZhengfu.setAdapter(zfFenleiAdapter);
    }
}