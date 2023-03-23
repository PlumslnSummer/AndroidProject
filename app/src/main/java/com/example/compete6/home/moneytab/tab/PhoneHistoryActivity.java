package com.example.compete6.home.moneytab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.ChongzhiAdapter;
import com.example.compete6.bean.ChongzhiBean;
import com.example.compete6.home.moneytab.MoneyTabActivity;
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

public class PhoneHistoryActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewChongzhi;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private ChongzhiAdapter chongzhiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_history);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        initView();
        initOther();
        if (token == null) {
            Toast.makeText(PhoneHistoryActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();

    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getHistoryPhone(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ChongzhiBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getHistoryPhone(res);
                    chongzhiAdapter.setRowsBeanList(rowsBeanList);
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
                Intent intent = new Intent(PhoneHistoryActivity.this, MoneyTabActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("交费历史记录页");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewChongzhi = (MyListView) findViewById(R.id.mylistview_chongzhi);
        chongzhiAdapter=new ChongzhiAdapter(this);
        mylistviewChongzhi.setAdapter(chongzhiAdapter);
    }
}