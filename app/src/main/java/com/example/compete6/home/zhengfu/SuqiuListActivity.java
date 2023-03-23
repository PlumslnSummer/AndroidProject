package com.example.compete6.home.zhengfu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.SuqiuListAdapter;
import com.example.compete6.bean.SuqiuListBean;
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

public class SuqiuListActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewSuqiu;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id;
    private SuqiuListAdapter suqiuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suqiu_list);
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
            Toast.makeText(SuqiuListActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getSuqiuList(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<SuqiuListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getSuqiuList(res);
                    suqiuListAdapter.setRowsBeanList(rowsBeanList);
                    suqiuListAdapter.setToken(token);
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
                Intent intent=new Intent(SuqiuListActivity.this, ZhengfuActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("诉求列表");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        mylistviewSuqiu = findViewById(R.id.mylistview_suqiu);
        suqiuListAdapter=new SuqiuListAdapter(this);
        mylistviewSuqiu.setAdapter(suqiuListAdapter);
    }
}