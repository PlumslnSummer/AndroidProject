package com.example.compete6.home.aixin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.AixinItenAdapter;
import com.example.compete6.adapter.AixintuijianAdapter;
import com.example.compete6.adapter.ZhiyuanBannerAdapter;
import com.example.compete6.bean.AixintuijianBean;
import com.example.compete6.bean.WuliucomBean;
import com.example.compete6.bean.ZhiyuanBannerBean;
import com.example.compete6.home.bus.BusThirdActivity;
import com.example.compete6.home.bus.SecondActivity;
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

public class AixinActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerAixin;
    private MyGridView mygridviewAixin;
    private MyListView mylistviewAixin;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private AixinItenAdapter aixinItenAdapter;
    private AixintuijianAdapter aixintuijianAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aixin);
        initView();
        initOther();
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        if(token==null){
            Toast.makeText(AixinActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
         retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                 .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initData();
        initBanner();
        
    }

    private void initBanner() {
        Call<ResponseBody> call=httpbinServices.getAixinBanner(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZhiyuanBannerBean.DataBean> dataBeanList= JsonParse.getmInstance().getZyBannerList(res);
                    bannerAixin.setImages(dataBeanList);
                    bannerAixin.setImageLoader(new ZhiyuanBannerAdapter());
                    bannerAixin.start();
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
        Call<ResponseBody> call=httpbinServices.getAixinItemList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<WuliucomBean.DataBean> dataBeanList=JsonParse.getmInstance().getWuliuComList(res);
                    aixinItenAdapter.setDataBeanList(dataBeanList);
                    aixinItenAdapter.setToken(token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });

        Call<ResponseBody> call1=httpbinServices.getAixintuijianList(token);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<AixintuijianBean.RowsBean> rowsBeanList=JsonParse.getmInstance().getAIxintuijianList(res);
                    aixintuijianAdapter.setRowsBeanList(rowsBeanList);
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
                Intent intent=new Intent(AixinActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("爱心公益");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        bannerAixin = (Banner) findViewById(R.id.banner_aixin);
        mygridviewAixin = (MyGridView) findViewById(R.id.mygridview_aixin);
        mylistviewAixin = (MyListView) findViewById(R.id.mylistview_aixin);
        aixinItenAdapter=new AixinItenAdapter(this);
        mygridviewAixin.setAdapter(aixinItenAdapter);
        aixintuijianAdapter=new AixintuijianAdapter(this);
        mylistviewAixin.setAdapter(aixintuijianAdapter);
    }
}