package com.example.compete6.home.qingnian;

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
import com.example.compete6.adapter.NeirongAdapter;
import com.example.compete6.bean.NeirongListBean;
import com.example.compete6.bean.RencaiDeBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyListView;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RencaiDeActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivRencaiDe;
    private TextView tvRencaiJj;
    private MyListView mylistviewRencaiDe;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id;
    private RencaiDeBean.DataBean dataBean;
    private NeirongAdapter neirongAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rencai_de);
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
            Toast.makeText(RencaiDeActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getRencaiDe(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getRencaiDe(res);
                    Glide.with(RencaiDeActivity.this)
                            .load(Contants.WEB_URL+dataBean.getImgUrl())
                            .error(R.mipmap.ic_launcher)
                            .into(ivRencaiDe);
                    tvRencaiJj.setText("简介："+dataBean.getIntroduce());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        Call<ResponseBody> call1=httpbinServices.getNeirList(token,id);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<NeirongListBean.DataBean> dataBeanList=JsonParse.getmInstance().getNeirong(res);
                    neirongAdapter.setDataBeanList(dataBeanList);
                    neirongAdapter.setToken(token);
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
                Intent intent=new Intent(RencaiDeActivity.this, QingnianActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("人才政策页");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        ivRencaiDe = findViewById(R.id.iv_rencai_de);
        tvRencaiJj = findViewById(R.id.tv_rencai_jj);
        mylistviewRencaiDe = findViewById(R.id.mylistview_rencai_de);
        neirongAdapter=new NeirongAdapter(this);
        mylistviewRencaiDe.setAdapter(neirongAdapter);
    }
}