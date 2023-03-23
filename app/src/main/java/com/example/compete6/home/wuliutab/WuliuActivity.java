package com.example.compete6.home.wuliutab;

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
import com.example.compete6.adapter.WuliuComAdapter;
import com.example.compete6.adapter.WuliuComAdapter2;
import com.example.compete6.adapter.ZhiyuanBannerAdapter;
import com.example.compete6.bean.WuliucomBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.home.wuliutab.tab.WuliuyundanActivity;
import com.example.compete6.home.zhiyuan.ZhiyuanActivity;
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

public class WuliuActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private SearchView search;
    private Banner bannerWuliu;
    private MyGridView mygridview;
    private MyListView mylistviewWuliu;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private WuliuComAdapter wuliuComAdapter;
    private WuliuComAdapter2 wuliuComAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliu);
        initView();
        initOther();
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        if(token==null){
            Toast.makeText(WuliuActivity.this,"请先登录",Toast.LENGTH_LONG).show();
            return;
        }
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);

        initData();
        initBanner();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(WuliuActivity.this, WuliuyundanActivity.class);
                intent1.putExtra("token",token);
                startActivity(intent1);
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
                    bannerWuliu.setImages(dataBeanList);
                    bannerWuliu.setImageLoader(new ZhiyuanBannerAdapter());
                    bannerWuliu.start();
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
       Call<ResponseBody> call=httpbinServices.getWuliuComList(token);
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   String res=response.body().string().trim();
                   List<WuliucomBean.DataBean> dataBeanList=JsonParse.getmInstance().getWuliuComList(res);
                   wuliuComAdapter.setDataBeanList(dataBeanList);
                   wuliuComAdapter2.setDataBeanList(dataBeanList);
                   wuliuComAdapter.setToken(token);
                   wuliuComAdapter2.setToken(token);
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
                Intent intent=new Intent(WuliuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("物流查询");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        search = (SearchView) findViewById(R.id.search);
        bannerWuliu = (Banner) findViewById(R.id.banner_wuliu);
        mygridview = (MyGridView) findViewById(R.id.mygridview);
        mylistviewWuliu = (MyListView) findViewById(R.id.mylistview_wuliu);
        wuliuComAdapter=new WuliuComAdapter(this);
        wuliuComAdapter2=new WuliuComAdapter2(this);
        mygridview.setAdapter(wuliuComAdapter);
        mylistviewWuliu.setAdapter(wuliuComAdapter2);
    }
}