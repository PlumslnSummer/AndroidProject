package com.example.compete6.home.worktab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.BannerAdapter;
import com.example.compete6.adapter.ZhaopinAdapter;
import com.example.compete6.adapter.ZhiweiAdapter;
import com.example.compete6.bean.BannerBean;
import com.example.compete6.bean.WorkZhiweiBean;
import com.example.compete6.bean.ZhaopinBean;
import com.example.compete6.home.tab.BannerTab0Activity;
import com.example.compete6.home.worktab.tab.ToudiActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyGridView;
import com.example.compete6.util.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkTabActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerWork;
    private SearchView searchWork;
    private MyGridView mygridviewZhiwei;
    private MyListView mylistviewWork;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private ZhiweiAdapter zhiweiAdapter;
    private ZhaopinAdapter zhaopinAdapter;
    private TextView tvToudi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_tab);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        initView();
        initOther();
        token = intent.getStringExtra("token");
        if (token == null) {
            Toast.makeText(WorkTabActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
        initBanner();
        tvToudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(WorkTabActivity.this, ToudiActivity.class);
                intent1.putExtra("token",token);
                startActivity(intent1);
            }
        });
    }

    private void initBanner() {
        Call<ResponseBody> call = httpbinServices.getHomeBanner();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<BannerBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getBannerList(res);
                    bannerWork.setImages(rowsBeanList);
                    bannerWork.setImageLoader(new BannerAdapter());
                    bannerWork.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int i) {
                            Intent intent = null;
                            intent = new Intent(WorkTabActivity.this, BannerTab0Activity.class);
                            intent.putExtra("id", rowsBeanList.get(i).getId());
                            startActivity(intent);
                        }
                    });
                    bannerWork.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initData() {
        Call<ResponseBody> call = httpbinServices.getZhiweiList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<WorkZhiweiBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getZhiweiname(res);
                    zhiweiAdapter.setRowsBeanList(rowsBeanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });

        Call<ResponseBody> call1 = httpbinServices.getJobList(token);
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res = response.body().string().trim();
                    List<ZhaopinBean.RowsBean> rowsBeanList = JsonParse.getmInstance().getZhaopinList(res);
                    zhaopinAdapter.setRowsBeanList(rowsBeanList);
                    zhaopinAdapter.setToken(token);
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
                Intent intent = new Intent(WorkTabActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("找工作");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        bannerWork = (Banner) findViewById(R.id.banner_work);
        searchWork = (SearchView) findViewById(R.id.search_work);
        mygridviewZhiwei = (MyGridView) findViewById(R.id.mygridview_zhiwei);
        mylistviewWork = (MyListView) findViewById(R.id.mylistview_work);
        zhiweiAdapter = new ZhiweiAdapter(this);
        mygridviewZhiwei.setAdapter(zhiweiAdapter);

        zhaopinAdapter = new ZhaopinAdapter(this);
        mylistviewWork.setAdapter(zhaopinAdapter);
        tvToudi = (TextView) findViewById(R.id.tv_toudi);
    }
}