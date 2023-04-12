package com.example.compete6.home.tab;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class BannerTab0Activity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivNewsDe;
    private TextView tvNewsContent;
    private TextView tvNewsLikenum;
    private TextView tvNewsReadnum;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;
    private NewsDetailBean.DataBean dataBean;
    private CharSequence charSequence=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_tab0);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        if(id==null){
            return;
        }
        initDta();
    }

    private void initDta() {
        Call<ResponseBody> call=httpbinServices.getHomeBannerNewsDetail(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getNewDetail(res);
                    tvTitle.setText(dataBean.getTitle());
                    Glide.with(BannerTab0Activity.this)
                            .load(Contants.WEB_URL+dataBean.getCover())
                            .error(R.mipmap.ic_launcher)
                            .into(ivNewsDe);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        charSequence= Html.fromHtml(dataBean.getContent(),Html.FROM_HTML_MODE_LEGACY);
                    }
                    tvNewsContent.setText(charSequence);
                    tvNewsLikenum.setText("点赞人数："+dataBean.getLikeNum());
                    tvNewsReadnum.setText("阅读人数："+dataBean.getReadNum());
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
                Intent intent = new Intent(BannerTab0Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivNewsDe = (ImageView) findViewById(R.id.iv_news_de);
        tvNewsContent = (TextView) findViewById(R.id.tv_news_content);
        tvNewsLikenum = (TextView) findViewById(R.id.tv_news_likenum);
        tvNewsReadnum = (TextView) findViewById(R.id.tv_news_readnum);
    }
}