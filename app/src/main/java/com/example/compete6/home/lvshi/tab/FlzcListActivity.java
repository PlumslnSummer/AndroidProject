package com.example.compete6.home.lvshi.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.FlzcAdapter;
import com.example.compete6.adapter.FlzcAdapter2;
import com.example.compete6.bean.FlzcBean;
import com.example.compete6.bean.LvshiTopListBean;
import com.example.compete6.home.aixin.AixinActivity;
import com.example.compete6.home.lvshi.LvshiActivity;
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

public class FlzcListActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyGridView mygridviewZclist;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private FlzcAdapter2 flzcAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flzc_list);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);

        initView();
        initOther();
        if(token==null){
            Toast.makeText(FlzcListActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getLvshiZcList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<FlzcBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getZcList(res);
                    flzcAdapter2.setRowsBeanList(rowsBeanList);
                    flzcAdapter2.setToken(token);
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
                Intent intent = new Intent(FlzcListActivity.this, LvshiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("法律专长");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mygridviewZclist = (MyGridView) findViewById(R.id.mygridview_zclist);
        flzcAdapter2=new FlzcAdapter2(this);
        mygridviewZclist.setAdapter(flzcAdapter2);
    }
}