package com.example.compete6.home.zhiyuan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.ZhiyuanBannerAdapter;
import com.example.compete6.adapter.ZhiyuanNewListAdapter;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.bean.ZhiyuanNewListBean;
import com.example.compete6.home.tab.BannerTab1Activity;
import com.example.compete6.home.zhiyuan.tab.MyPartyActivity;
import com.example.compete6.home.zhiyuan.tab.ZhiyuanPartyActivity;
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

public class ZhiyuanActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerZhiyuan;
    private MyListView mylistviewZhiyuan;
    private LinearLayout linearZyParty;
    private ImageView ivZyParty;
    private LinearLayout linearMyparty;
    private ImageView ivMyparty;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private ZhiyuanNewListAdapter zhiyuanNewListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhiyuan);
        initView();
        initOther();
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        if(token==null){
            Toast.makeText(ZhiyuanActivity.this,"请先登录",Toast.LENGTH_LONG).show();
            return;
        }
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);

        initBanner();

        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getZyNewList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZhiyuanNewListBean.RowsBean> rowsBeanList=JsonParse.getmInstance().getZyNewList(res);
                    zhiyuanNewListAdapter.setRowsBeanList(rowsBeanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        zhiyuanNewListAdapter=new ZhiyuanNewListAdapter(this);
        mylistviewZhiyuan.setAdapter(zhiyuanNewListAdapter);
    }

    private void initBanner() {
        Call<ResponseBody> call=httpbinServices.getZyBanner(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZhiyuanBannerBean.DataBean> dataBeanList= JsonParse.getmInstance().getZyBannerList(res);
                    bannerZhiyuan.setImages(dataBeanList);
                    bannerZhiyuan.setImageLoader(new ZhiyuanBannerAdapter());
                    bannerZhiyuan.start();
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
                Intent intent=new Intent(ZhiyuanActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("志愿活动");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        bannerZhiyuan = (Banner) findViewById(R.id.banner_zhiyuan);
        mylistviewZhiyuan = (MyListView) findViewById(R.id.mylistview_zhiyuan);
        linearZyParty = (LinearLayout) findViewById(R.id.linear_zy_party);
        ivZyParty = (ImageView) findViewById(R.id.iv_zy_party);
        linearMyparty = (LinearLayout) findViewById(R.id.linear_myparty);
        ivMyparty = (ImageView) findViewById(R.id.iv_myparty);

        linearZyParty.setOnClickListener(this);
        linearMyparty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.linear_zy_party:
                intent=new Intent(ZhiyuanActivity.this, ZhiyuanPartyActivity.class);
                intent.putExtra("token",token);
                break;
            case R.id.linear_myparty:
                intent=new Intent(ZhiyuanActivity.this, MyPartyActivity.class);
                intent.putExtra("token",token);
                break;
        }
        startActivity(intent);
    }
}