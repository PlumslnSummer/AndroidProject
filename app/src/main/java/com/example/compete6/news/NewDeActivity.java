package com.example.compete6.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.NewsDetailBean;
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

public class NewDeActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitleTop;
    private ImageView ivNewsDe;
    private TextView tvNewsContentDe;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Intent intent;
    private NewsDetailBean.DataBean dataBean;
    private Integer id;
    private CharSequence charSequence=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_de);
        retrofit=new Retrofit.Builder().baseUrl(Contants.WEB_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        intent=getIntent();
        id=intent.getIntExtra("id",-1);
        token=intent.getStringExtra("token");
        if(token==null){
            Toast.makeText(this,"未登录",Toast.LENGTH_SHORT).show();
        }
        initData();
        initView();
        initBack();
    }

    private void initBack() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentback=new Intent(NewDeActivity.this, MainActivity.class);
                startActivity(intentback);
            }
        });

    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.GetNewListDetail(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getNewsDe(json);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        charSequence= Html.fromHtml(dataBean.getContent(),Html.FROM_HTML_MODE_LEGACY);
                    }
                    Glide.with(NewDeActivity.this)
                            .load(Contants.WEB_URL+dataBean.getCover())
                            .error(R.mipmap.ic_launcher)
                            .into(ivNewsDe);
                    tvNewsContentDe.setText(charSequence);
                    tvTitleTop.setText(dataBean.getTitle());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitleTop = findViewById(R.id.tv_title);
        ivNewsDe = findViewById(R.id.iv_news_de);
        tvNewsContentDe = findViewById(R.id.tv_news_content_de);
    }
}