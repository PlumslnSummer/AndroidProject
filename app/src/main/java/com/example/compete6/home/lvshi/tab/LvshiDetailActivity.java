package com.example.compete6.home.lvshi.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.MyFragmentAdapter;
import com.example.compete6.bean.LvshiDetailBean;
import com.example.compete6.home.lvshi.LvshiActivity;
import com.example.compete6.home.lvshi.tab.pager.FuwuBlankFragment;
import com.example.compete6.home.lvshi.tab.pager.PingjiaBlankFragment;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LvshiDetailActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivLvshi;
    private TextView tvLvshiname;
    private TextView tvFlzc;
    private TextView tvServicestime;
    private TextView tvFavo;
    private TextView tvFuwu;
    private TextView tvPingjia,tv_current;
    private ViewPager2 viewLvshi;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;
    private LvshiDetailBean.DataBean dataBean;
    private MyFragmentAdapter myFragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvshi_detail);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        id=intent.getIntExtra("id",-1);
        initView();

        initOther();
        if(token==null){
            Toast.makeText(LvshiDetailActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
        initpager();
    }

    private void initpager() {
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(FuwuBlankFragment.newInstance(token, String.valueOf(id)));
        fragments.add(PingjiaBlankFragment.newInstance(token,String.valueOf(id)));
        myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewLvshi.setAdapter(myFragmentAdapter);
        viewLvshi.setUserInputEnabled(false);
        viewLvshi.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        tv_current.setSelected(false);
        switch (position){
            case R.id.tv_fuwu:
                viewLvshi.setCurrentItem(0);
            case 0:
                tv_current=tvFuwu;
                tvFuwu.setSelected(true);
                break;
            case R.id.tv_pingjia:
                viewLvshi.setCurrentItem(1);
            case 1:
                tv_current=tvPingjia;
                tvPingjia.setSelected(true);
                break;
        }
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getLvshidetail(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getLvshiDetail(res);
                    Glide.with(LvshiDetailActivity.this)
                            .load(Contants.WEB_URL+dataBean.getAvatarUrl())
                            .error(R.drawable.head)
                            .into(ivLvshi);
                    tvLvshiname.setText("律师名称："+dataBean.getName());
                    tvFlzc.setText("法律专长："+dataBean.getLegalExpertiseName());
                    tvServicestime.setText("咨询人数："+dataBean.getServiceTimes());
                    tvFavo.setText("服务人数："+dataBean.getFavorableRate());
                    
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
                Intent intent = new Intent(LvshiDetailActivity.this, LvshiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("律师详情页");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivLvshi = (ImageView) findViewById(R.id.iv_lvshi);
        tvLvshiname = (TextView) findViewById(R.id.tv_lvshiname);
        tvFlzc = (TextView) findViewById(R.id.tv_flzc);
        tvServicestime = (TextView) findViewById(R.id.tv_servicestime);
        tvFavo = (TextView) findViewById(R.id.tv_favo);
        tvFuwu = (TextView) findViewById(R.id.tv_fuwu);
        tvPingjia = (TextView) findViewById(R.id.tv_pingjia);
        viewLvshi = (ViewPager2) findViewById(R.id.view_lvshi);
        tv_current=tvFuwu;
        tvFuwu.setSelected(true);

        tvFuwu.setOnClickListener(this);
        tvPingjia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }
}
