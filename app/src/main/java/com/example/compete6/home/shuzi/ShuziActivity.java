package com.example.compete6.home.shuzi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.SHuziAdapter;
import com.example.compete6.bean.ShuziListBean;
import com.example.compete6.home.movie.MovieTabActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyListView;
import com.example.compete6.util.RefreshListView;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShuziActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private RefreshListView mylistviewShuzi;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private SHuziAdapter sHuziAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuzi);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        initView();
        initOther();
        if(token==null){
            Toast.makeText(ShuziActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getShuziList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ShuziListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getShuziList(res);
                    sHuziAdapter.setRowsBeanList(rowsBeanList);
                    sHuziAdapter.setToken(token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        mylistviewShuzi.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void RefeshData() {
                initData();
                mylistviewShuzi.loadFinish();
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShuziActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("数字图书馆");
    }


    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        mylistviewShuzi = findViewById(R.id.mylistview_shuzi);
        sHuziAdapter=new SHuziAdapter(this);
        mylistviewShuzi.setAdapter(sHuziAdapter);
    }
}