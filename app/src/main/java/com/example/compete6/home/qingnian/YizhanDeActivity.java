package com.example.compete6.home.qingnian;

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
import com.example.compete6.bean.YizhanDeBean;
import com.example.compete6.home.shuzi.PInglunActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YizhanDeActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerYizhan;
    private TextView tvAddress;
    private TextView tvPhone;
    private TextView tvRuzhu;
    private TextView tvBoy;
    private TextView tvGril;
    private TextView tvJj;
    private TextView tvPz;
    private TextView tvZb;
    private TextView tvTs;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id;
    private YizhanDeBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yizhan_de);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        id=intent.getIntExtra("id",-1);
        initView();
        initOther();
        if(token==null){
            Toast.makeText(YizhanDeActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getYizhanDe(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getYizhanDe(res);
                    bannerYizhan.setImages(dataBean.getImageUrlList());
                    bannerYizhan.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            Glide.with(context)
                                    .load(Contants.WEB_URL+o)
                                    .error(R.mipmap.ic_launcher)
                                    .into(imageView);
                        }
                    });
                    bannerYizhan.start();
                    tvAddress.setText("地址："+dataBean.getAddress());
                    tvPhone.setText("联系电话："+dataBean.getPhone());
                    tvRuzhu.setText("入住时间段："+dataBean.getWorkTime());
                    tvBoy.setText("男："+dataBean.getBedsCountBoy());
                    tvGril.setText("女："+dataBean.getBedsCountGirl());
                    tvJj.setText("驿站简介："+dataBean.getIntroduce());
                    tvPz.setText("房间配置："+dataBean.getInternalFacilities());
                    tvZb.setText("周边配套："+dataBean.getSurroundingFacilities());
                    tvTs.setText("特色服务："+dataBean.getSpecialService());
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
                Intent intent=new Intent(YizhanDeActivity.this, QingnianActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("驿站详情页");
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        bannerYizhan = findViewById(R.id.banner_yizhan);
        tvAddress = findViewById(R.id.tv_address);
        tvPhone = findViewById(R.id.tv_phone);
        tvRuzhu = findViewById(R.id.tv_ruzhu);
        tvBoy = findViewById(R.id.tv_boy);
        tvGril = findViewById(R.id.tv_gril);
        tvJj = findViewById(R.id.tv_jj);
        tvPz = findViewById(R.id.tv_pz);
        tvZb = findViewById(R.id.tv_zb);
        tvTs = findViewById(R.id.tv_ts);
    }
}