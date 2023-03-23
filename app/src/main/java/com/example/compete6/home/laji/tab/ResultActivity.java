package com.example.compete6.home.laji.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.ResultAdapter;
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

public class ResultActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyGridView mygridviewLaji;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        initView();
        initOther();
        if (token == null) {
            Toast.makeText(ResultActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getResultList(token,8);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ResultBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getResult(res);
                    resultAdapter.setRowsBeanList(rowsBeanList);
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
                Intent intent = new Intent(ResultActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("结果页");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        mygridviewLaji = findViewById(R.id.mygridview_laji);
        resultAdapter=new ResultAdapter(this);
        mygridviewLaji.setAdapter(resultAdapter);
    }
}