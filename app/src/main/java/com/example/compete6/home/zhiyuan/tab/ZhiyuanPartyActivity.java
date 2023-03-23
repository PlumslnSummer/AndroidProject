package com.example.compete6.home.zhiyuan.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.ZyPartAdapter;
import com.example.compete6.bean.ZyPartyListBean;
import com.example.compete6.home.zhiyuan.ZhiyuanActivity;
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

public class ZhiyuanPartyActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewParty;
    private ZyPartAdapter zyPartAdapter;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhiyuan_party);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initOther();

        if(token==null){
            Toast.makeText(ZhiyuanPartyActivity.this,"请先登录",Toast.LENGTH_LONG).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getZyPartyList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<ZyPartyListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getZypartyList(res);
                    zyPartAdapter.setRowsBeanList(rowsBeanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        zyPartAdapter=new ZyPartAdapter(this);
        mylistviewParty.setAdapter(zyPartAdapter);
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhiyuanPartyActivity.this, ZhiyuanActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("活动列表");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewParty = (MyListView) findViewById(R.id.mylistview_party);
    }
}