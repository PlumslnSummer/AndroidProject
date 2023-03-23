package com.example.compete6.home.worktab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.compete6.R;
import com.example.compete6.adapter.MyFragmentAdapter;
import com.example.compete6.home.worktab.WorkTabActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JianliActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvChaxun;
    private TextView tvAdd;
    private TextView tvBianji,tv_current;
    private ViewPager2 viewJianli;
    private String token;
    private MyFragmentAdapter myFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianli);

        Intent intent = getIntent();
        initView();
        initOther();
        token = intent.getStringExtra("token");
        if (token == null) {
            Toast.makeText(JianliActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initPager();
    }

    private void initPager() {
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(CheckBlankFragment.newInstance(token,"2"));
        fragments.add(AddBlankFragment.newInstance(token,"2"));
        fragments.add(PutBlankFragment.newInstance(token,"2"));
        myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewJianli.setAdapter(myFragmentAdapter);
        viewJianli.setUserInputEnabled(false);
        viewJianli.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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
            case R.id.tv_chaxun:
                viewJianli.setCurrentItem(0);
            case 0:
                tv_current=tvChaxun;
                tvChaxun.setSelected(true);
                break;
            case R.id.tv_add:
                viewJianli.setCurrentItem(1);
            case 1:
                tv_current=tvAdd;
                tvAdd.setSelected(true);
                break;
            case R.id.tv_bianji:
                viewJianli.setCurrentItem(2);
            case 2:
                tv_current=tvBianji;
                tvBianji.setSelected(true);
                break;
        }
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JianliActivity.this, JobDetailActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("个人简历页面");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvChaxun = (TextView) findViewById(R.id.tv_chaxun);
        tvAdd = (TextView) findViewById(R.id.tv_add);
        tvBianji = (TextView) findViewById(R.id.tv_bianji);
        viewJianli = (ViewPager2) findViewById(R.id.view_jianli);

        tv_current=tvChaxun;
        tvChaxun.setSelected(true);

        tvChaxun.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvBianji.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }
}