package com.example.compete6.home.metrotab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.MetroAllStationListAdapter;
import com.example.compete6.bean.MetroDeBean;
import com.example.compete6.home.metrotab.MetroActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyListView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MetroDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvFirst;
    private TextView tvEnd;
    private TextView tvRuntime;
    private TextView tvRunnumber;
    private TextView tvRunkm;
    private String token;
    private Integer id;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private MetroDeBean.DataBean dataBean;
    private MetroAllStationListAdapter metroAllStationListAdapter;
    private MyListView mylistviewStation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro_detail);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        initView();
        initOther();
        token = intent.getStringExtra("token");
        id = intent.getIntExtra("id", -1);
        if (token == null) {
            Toast.makeText(MetroDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getMetroDe(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    dataBean = JsonParse.getmInstance().getMetroDe(res);
                    tvTitle.setText(dataBean.getName());
                    tvFirst.setText("起始站：" + dataBean.getFirst());
                    tvEnd.setText("终点站：" + dataBean.getEnd());
                    tvRuntime.setText("剩余时间：" + dataBean.getRemainingTime() + "分钟");
                    tvRunnumber.setText("间隔站数：" + dataBean.getStationsNumber());
                    tvRunkm.setText("间隔距离：" + dataBean.getKm() + "km");
                    metroAllStationListAdapter.setStation(dataBean.getRunStationsName());
                    metroAllStationListAdapter.setMetroStepListBeanList(dataBean.getMetroStepList());
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
                Intent intent = new Intent(MetroDetailActivity.this, MetroActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvFirst = (TextView) findViewById(R.id.tv_first);
        tvEnd = (TextView) findViewById(R.id.tv_end);
        tvRuntime = (TextView) findViewById(R.id.tv_runtime);
        tvRunnumber = (TextView) findViewById(R.id.tv_runnumber);
        tvRunkm = (TextView) findViewById(R.id.tv_runkm);
        metroAllStationListAdapter = new MetroAllStationListAdapter(this);

        mylistviewStation = (MyListView) findViewById(R.id.mylistview_station);
        mylistviewStation.setAdapter(metroAllStationListAdapter);
    }
}