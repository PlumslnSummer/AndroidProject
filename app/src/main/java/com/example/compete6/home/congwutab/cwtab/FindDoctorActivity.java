package com.example.compete6.home.congwutab.cwtab;

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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.compete6.R;
import com.example.compete6.adapter.FinDtdAdapter;
import com.example.compete6.bean.FindDtBean;
import com.example.compete6.home.congwutab.CongwuActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.LoadMoreOnScrollListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FindDoctorActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private RecyclerView mylistviewFind;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private FinDtdAdapter finDtdAdapter;
    private int page = 0;
    private int index = 0;
    private LinearLayout linearTop;
    private ProgressBar progressbar;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");

        initView();
        initOther();
        if (token == null) {
            Toast.makeText(FindDoctorActivity.this, "请先登录", Toast.LENGTH_LONG).show();
            return;
        }
        initData();

    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getCwyishengList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    page++;
                    String res = response.body().string().trim();
                    List<FindDtBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getDoctorList(res);

                    finDtdAdapter.setRowsBeanList(rowsBeanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });

        finDtdAdapter.OnItemClickFangfa(new FinDtdAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FindDtBean.RowsBean rowsBean) {
                Intent intent = new Intent(FindDoctorActivity.this, WenzhenActivity.class);
                intent.putExtra("doctor", rowsBean);
                startActivity(intent);
            }
        });

    }

    private void delay() {
        progressbar.setVisibility(View.VISIBLE);
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progressbar.setVisibility(View.GONE);
//                initData();
//            }
//        },2000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressbar.setVisibility(View.GONE);
                            initData();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this, CongwuActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("找医生");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewFind = findViewById(R.id.mylistview_find);
        finDtdAdapter = new FinDtdAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mylistviewFind.setLayoutManager(linearLayoutManager);
        mylistviewFind.setItemAnimator(new DefaultItemAnimator());
        // mylistviewFind.setLayoutManager(new GridLayoutManager(this,2));
        mylistviewFind.setAdapter(finDtdAdapter);
        linearTop = findViewById(R.id.linear_top);
        progressbar = findViewById(R.id.progressbar);
        mylistviewFind.addOnScrollListener(new LoadMoreOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                if (currentPage > page) {
                    delay();

                }

            }
        });
        delay();

    }
}