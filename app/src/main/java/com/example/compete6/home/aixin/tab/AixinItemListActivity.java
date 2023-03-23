package com.example.compete6.home.aixin.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.adapter.GongyiListAdapter;
import com.example.compete6.bean.GongyiListBean;
import com.example.compete6.home.aixin.AixinActivity;
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

public class AixinItemListActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewGongyi;
    private GongyiListAdapter gongyiListAdapter;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aixin_item_list);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        Intent intent=getIntent();
        id=intent.getIntExtra("id",-1);
        token=intent.getStringExtra("token");
        if(id==null){
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getGongyiList(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<GongyiListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getGongyiList(res);
                    gongyiListAdapter.setRowsBeanList(rowsBeanList);
                    gongyiListAdapter.setRes(res);
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
                Intent intent=new Intent(AixinItemListActivity.this, AixinActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("公益列表页");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewGongyi = (MyListView) findViewById(R.id.mylistview_gongyi);
        gongyiListAdapter=new GongyiListAdapter(this);
        mylistviewGongyi.setAdapter(gongyiListAdapter);
    }
}