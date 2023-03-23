package com.example.compete6.home.qingnian;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.NeirongDeBean;
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

public class NeirRongActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvName;
    private TextView tvRiqi;
    private TextView tvContent;
    private TextView tvAuthor;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id;
    private CharSequence charSequence=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neir_rong);
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
            Toast.makeText(NeirRongActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getNeirongDe(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    NeirongDeBean.DataBean dataBean= JsonParse.getmInstance().getNeirongde(res);
                    tvName.setText("标题："+dataBean.getTitle());
                    tvRiqi.setText("日期："+dataBean.getCreateTime());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        charSequence= Html.fromHtml(dataBean.getContent(),Html.FROM_HTML_MODE_LEGACY);
                    }
                    tvContent.setText("内容："+charSequence);
                    tvAuthor.setText("作者："+dataBean.getAuthor());
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
                Intent intent=new Intent(NeirRongActivity.this, RencaiDeActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("内容页");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        tvName = findViewById(R.id.tv_name);
        tvRiqi = findViewById(R.id.tv_riqi);
        tvContent = findViewById(R.id.tv_content);
        tvAuthor = findViewById(R.id.tv_author);
    }
}