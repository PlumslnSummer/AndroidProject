package com.example.compete6.home.worktab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.ToudiAdapter;
import com.example.compete6.bean.ToudiListBean;
import com.example.compete6.home.worktab.WorkTabActivity;
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

public class ToudiActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewToudi;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private ToudiAdapter toudiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toudi);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        initView();
        initOther();
        token = intent.getStringExtra("token");
        if (token == null) {
            Toast.makeText(ToudiActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        iniData();
    }

    private void iniData() {
        Call<ResponseBody> call=httpbinServices.gettouList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ToudiListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getToudiList(res);
                    toudiAdapter.setRowsBeanList(rowsBeanList);
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
                Intent intent = new Intent(ToudiActivity.this, WorkTabActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("投递记录页面");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewToudi = (MyListView) findViewById(R.id.mylistview_toudi);
        toudiAdapter=new ToudiAdapter(this);
        mylistviewToudi.setAdapter(toudiAdapter);
    }
}