package com.example.compete6.home.shuzi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.LibraryBean;
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

public class SHuziDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivLibrary;
    private TextView tvName;
    private TextView tvAddress;
    private TextView tvJs;
    private TextView tvTimeShuzi;
    private TextView tvState;
    private Button btnPinglun;
    private String token;
    private Integer id;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private LibraryBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_huzi_detail);
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
            Toast.makeText(SHuziDetailActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getLibraryDe(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getLibraryDe(res);
                    Glide.with(SHuziDetailActivity.this)
                            .load(Contants.WEB_URL+dataBean.getImgUrl())
                            .error(R.mipmap.ic_launcher)
                            .into(ivLibrary);
                    tvName.setText(dataBean.getName());
                    tvAddress.setText("地址："+dataBean.getAddress());
                    tvJs.setText("图书馆介绍："+dataBean.getDescription());
                    tvTimeShuzi.setText("营业时间："+dataBean.getBusinessHours());
                    tvState.setText("营业状态："+dataBean.getBusinessState());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        btnPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SHuziDetailActivity.this,PInglunActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SHuziDetailActivity.this, ShuziActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("图书馆详情");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        ivLibrary = findViewById(R.id.iv_library);
        tvName = findViewById(R.id.tv_name);
        tvAddress = findViewById(R.id.tv_address);
        tvJs = findViewById(R.id.tv_js);
        tvTimeShuzi = findViewById(R.id.tv_time_shuzi);
        tvState = findViewById(R.id.tv_state);
        btnPinglun = findViewById(R.id.btn_pinglun);
    }
}