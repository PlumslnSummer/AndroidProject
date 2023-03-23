package com.example.compete6.home.metrotab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.MetroHomeListAdapter;
import com.example.compete6.bean.MetroHomeListBean;
import com.example.compete6.home.metrotab.tab.AllLinesActivity;
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

public class MetroActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewMetro;
    private MetroHomeListAdapter metroHomeListAdapter;
    private Retrofit retrofit;
    private HttpbinServices httpbinServicesl;
    private String token;
    private LinearLayout linearTop;
    private LinearLayout ivLines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServicesl = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        initView();
        initOther();
        if (token == null) {
            Toast.makeText(MetroActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
        ivLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MetroActivity.this, AllLinesActivity.class);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServicesl.getMetroHomeList(token, "建国门");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<MetroHomeListBean.DataBean> dataBeanList = JsonParse.getmInstance().getMetroList(res);
                    metroHomeListAdapter.setDataBeanList(dataBeanList);
                    metroHomeListAdapter.setToken(token);
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
                Intent intent = new Intent(MetroActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("地铁查询");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewMetro = (MyListView) findViewById(R.id.mylistview_metro);
        metroHomeListAdapter = new MetroHomeListAdapter(this);
        mylistviewMetro.setAdapter(metroHomeListAdapter);
        linearTop = (LinearLayout) findViewById(R.id.linear_top);
        ivLines = (LinearLayout) findViewById(R.id.iv_lines);
    }
}