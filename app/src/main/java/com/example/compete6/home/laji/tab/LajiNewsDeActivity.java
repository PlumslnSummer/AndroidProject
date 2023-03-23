package com.example.compete6.home.laji.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.bean.LajiNewsDeBean;
import com.example.compete6.home.laji.LajiActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LajiNewsDeActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvTime;
    private TextView tvContent;
    private Button btnFabu;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;
    private TextView tvTitle1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laji_news_de);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        id = intent.getIntExtra("id", -1);
        initView();
        initOther();
        if (token == null) {
            Toast.makeText(LajiNewsDeActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getLsjiNewsDe(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    LajiNewsDeBean.DataBean dataBean = JsonParse.getmInstance().getLajiDe(res);
                    tvTitle1.setText("标题："+dataBean.getTitle());
                    tvAuthor.setText("发布人："+dataBean.getAuthor());
                    tvTime.setText("发布时间："+dataBean.getCreateTime());
                    tvContent.setText("正文："+ dataBean.getContent());
                    btnFabu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(LajiNewsDeActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                        }
                    });
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
                Intent intent = new Intent(LajiNewsDeActivity.this, LajiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("新闻详情");
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvTime = findViewById(R.id.tv_time);
        tvContent = findViewById(R.id.tv_content);
        btnFabu = findViewById(R.id.btn_fabu);
        tvTitle1 = findViewById(R.id.tv_title1);
    }
}