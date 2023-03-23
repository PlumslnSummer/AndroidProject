package com.example.compete6.home.congwutab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.AnliAdapter;
import com.example.compete6.bean.Anlibean;
import com.example.compete6.home.congwutab.cwtab.FindDoctorActivity;
import com.example.compete6.home.congwutab.cwtab.WenDetailActivity;
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

public class CongwuActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private LinearLayout linearCwys;
    private MyListView mylistviewAnli;
    private AnliAdapter anliAdapter;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private LinearLayout linearTop;
    private ProgressBar progressCw;
    private Handler handler;
    private TextView tvWenzhen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congwu);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        handler = new Handler();
        initView();
        initOther();
        if (token == null) {
            Toast.makeText(CongwuActivity.this, "请先登录", Toast.LENGTH_LONG).show();
            return;
        }
        initData();
        linearCwys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CongwuActivity.this, FindDoctorActivity.class);
                intent1.putExtra("token", token);
                startActivity(intent1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressCw.setVisibility(View.GONE);
            }
        }, 3000);
        tvWenzhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CongwuActivity.this, WenDetailActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getAnliList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<Anlibean.RowsBean> rowsBeanList = JsonParse.getmInstance().getAlwentiList(res);
                    anliAdapter.setRowsBeanList(rowsBeanList);
                    anliAdapter.setRes(res);
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
                Intent intent = new Intent(CongwuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("宠物医院");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        linearCwys = (LinearLayout) findViewById(R.id.linear_cwys);
        mylistviewAnli = (MyListView) findViewById(R.id.mylistview_anli);
        anliAdapter = new AnliAdapter(this);
        mylistviewAnli.setAdapter(anliAdapter);
        linearTop = (LinearLayout) findViewById(R.id.linear_top);
        progressCw = (ProgressBar) findViewById(R.id.progress_cw);
        tvWenzhen = (TextView) findViewById(R.id.tv_wenzhen);
    }
}