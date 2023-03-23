package com.example.compete6.home.qingnian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.RencaiAdapter;
import com.example.compete6.adapter.YizhanAdapter;
import com.example.compete6.bean.RencaiBean;
import com.example.compete6.bean.YizhanBean;
import com.example.compete6.home.shuzi.PInglunActivity;
import com.example.compete6.home.shuzi.SHuziDetailActivity;
import com.example.compete6.home.shuzi.ShuziActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyGridView;
import com.example.compete6.util.MyListView;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QingnianActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyGridView gridErncai;
    private MyListView mylistviewYizhan;
    private String token;
    private RencaiAdapter rencaiAdapter;
    private YizhanAdapter yizhanAdapter;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qingnian);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        initView();
        initOther();
        if(token==null){
            Toast.makeText(QingnianActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getRencaiList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<RencaiBean.DataBean> dataBeanList= JsonParse.getmInstance().getRencai(res);
                    rencaiAdapter.setDataBeanList(dataBeanList);
                    rencaiAdapter.setToken(token);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });

        Call<ResponseBody> call1=httpbinServices.getYIzhanList(token);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<YizhanBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getYizhan(res);
                    yizhanAdapter.setRowsBeanList(rowsBeanList);
                    yizhanAdapter.setToken(token);
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
                Intent intent=new Intent(QingnianActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("青年驿站");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        gridErncai = findViewById(R.id.grid_erncai);
        mylistviewYizhan = findViewById(R.id.mylistview_yizhan);
        rencaiAdapter=new RencaiAdapter(this);
        gridErncai.setAdapter(rencaiAdapter);
        yizhanAdapter=new YizhanAdapter(this);
        mylistviewYizhan.setAdapter(yizhanAdapter);
    }
}