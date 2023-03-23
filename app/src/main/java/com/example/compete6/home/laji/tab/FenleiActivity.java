package com.example.compete6.home.laji.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.ChangjianAdapter;
import com.example.compete6.adapter.FenleiAdapter;
import com.example.compete6.adapter.LajiFenAdapter;
import com.example.compete6.bean.FenleiBean;
import com.example.compete6.bean.ResultBean;
import com.example.compete6.home.laji.LajiActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyGridView;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FenleiActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyGridView mygridviewFenlei;
    private MyGridView mygridviewChangjain;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private ChangjianAdapter changjianAdapter;
    private FenleiAdapter fenleiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        initView();
        initOther();
        if (token == null) {
            Toast.makeText(FenleiActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
        initChangjian();
    }

    private void initChangjian() {
        Call<ResponseBody> call=httpbinServices.getChangjianList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ResultBean.RowsBean> rowsBeanList=JsonParse.getmInstance().getResult(res);
                    changjianAdapter.setRowsBeanList(rowsBeanList);
                    changjianAdapter.setToken(token);
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
        Call<ResponseBody> call=httpbinServices.getFenleiList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<FenleiBean.RowsBean> rowsBeanList=JsonParse.getmInstance().getFenleiList(res);
                    fenleiAdapter.setRowsBeanList(rowsBeanList);
                    fenleiAdapter.setToken(token);
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
                Intent intent = new Intent(FenleiActivity.this, LajiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("分类页");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        mygridviewFenlei = findViewById(R.id.mygridview_fenlei);
        mygridviewChangjain = findViewById(R.id.mygridview_changjain);
        fenleiAdapter=new FenleiAdapter(this);
        mygridviewFenlei.setAdapter(fenleiAdapter);
        changjianAdapter=new ChangjianAdapter(this);
        mygridviewChangjain.setAdapter(changjianAdapter);
    }
}