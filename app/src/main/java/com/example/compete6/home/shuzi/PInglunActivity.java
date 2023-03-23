package com.example.compete6.home.shuzi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.PinglunAdapter;
import com.example.compete6.bean.PinglunBean;
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

public class PInglunActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private EditText etPl;
    private Button btnFabiao;
    private MyListView mylistviewPl;
    private String token;
    private Integer id;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private PinglunAdapter pinglunAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_inglun);
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
            Toast.makeText(PInglunActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getPinglunList(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<PinglunBean.DataBean> dataBeanList= JsonParse.getmInstance().getPinglun(res);
                    pinglunAdapter.setDataBeanList(dataBeanList);
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
                Intent intent=new Intent(PInglunActivity.this, SHuziDetailActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("评论页面");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        etPl = findViewById(R.id.et_pl);
        btnFabiao = findViewById(R.id.btn_fabiao);
        mylistviewPl = findViewById(R.id.mylistview_pl);
        pinglunAdapter=new PinglunAdapter(this);
        mylistviewPl.setAdapter(pinglunAdapter);
    }
}