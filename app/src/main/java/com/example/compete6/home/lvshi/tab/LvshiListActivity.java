package com.example.compete6.home.lvshi.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.LvshiTopListAdapter;
import com.example.compete6.adapter.LvshiZcListAdapter;
import com.example.compete6.bean.LvshiZcListBean;
import com.example.compete6.home.lvshi.LvshiActivity;
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

public class LvshiListActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewLvshi;
    private LvshiZcListAdapter lvshiZcListAdapter;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvshi_list);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        id=intent.getIntExtra("id",-1);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        if(token==null){
            return;
        }
        initData();
        
    }

    private void initData() {
        Call<ResponseBody> call=null;
        if(id==-1){
            call=httpbinServices.getAllLvshiList(token);
        }else {
            call=httpbinServices.getLvshizcList(token,id);
        }


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<LvshiZcListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getLvshiZcList(res);
                    lvshiZcListAdapter.setRowsBeanList(rowsBeanList);
                    lvshiZcListAdapter.setToken(token);
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
                Intent intent = new Intent(LvshiListActivity.this, FlzcListActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("律师列表");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewLvshi = (MyListView) findViewById(R.id.mylistview_lvshi);
        lvshiZcListAdapter=new LvshiZcListAdapter(this);
        mylistviewLvshi.setAdapter(lvshiZcListAdapter);
    }
}