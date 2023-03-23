package com.example.compete6.home.lvshi.tab.zixun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.ZixunAdapter;
import com.example.compete6.bean.ZixunListBean;
import com.example.compete6.home.lvshi.LvshiActivity;
import com.example.compete6.home.lvshi.tab.FlzcListActivity;
import com.example.compete6.home.lvshi.tab.LvshiListActivity;
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

public class ZixunActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvYs;
    private TextView tvDs;
    private MyListView mylistviewZixun;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private ZixunAdapter zixunAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixun);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getZixunList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZixunListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getZixunList(res);
                    zixunAdapter.setRowsBeanList(rowsBeanList);
                    zixunAdapter.setToken(token);
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
                Intent intent = new Intent(ZixunActivity.this, LvshiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("咨询列表");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvYs = (TextView) findViewById(R.id.tv_ys);
        tvDs = (TextView) findViewById(R.id.tv_ds);
        mylistviewZixun = (MyListView) findViewById(R.id.mylistview_zixun);
        zixunAdapter=new ZixunAdapter(this);
        mylistviewZixun.setAdapter(zixunAdapter);
        tvYs.setSelected(true);
    }
}