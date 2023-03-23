package com.example.compete6.home.zhiyuan.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.compete6.R;
import com.example.compete6.adapter.MyFragmentAdapter;
import com.example.compete6.home.zhiyuan.ZhiyuanActivity;
import com.example.compete6.home.zhiyuan.tab.mytab.CancelBlankFragment;
import com.example.compete6.home.zhiyuan.tab.mytab.FinshBlankFragment;
import com.example.compete6.home.zhiyuan.tab.mytab.SubmitBlankFragment;

import java.util.ArrayList;

public class MyPartyActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvPartyclass0;
    private TextView tvPartyclass1;
    private TextView tvPartyclass2,tv_current;
    private ViewPager2 viewZy;
    private MyFragmentAdapter myFragmentAdapter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_party);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        initView();
        initOther();
        initpager();
    }

    private void initpager() {
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(FinshBlankFragment.newInstance(token,"2"));
        fragments.add(SubmitBlankFragment.newInstance("1","2"));
        fragments.add(CancelBlankFragment.newInstance("1","2"));
        myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewZy.setAdapter(myFragmentAdapter);
        viewZy.setUserInputEnabled(false);
        viewZy.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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
            case R.id.tv_partyclass0:
                viewZy.setCurrentItem(0);
            case 0:
                tv_current=tvPartyclass0;
                tvPartyclass0.setSelected(true);
                break;
            case R.id.tv_partyclass1:
                viewZy.setCurrentItem(1);
            case 1:
                tv_current=tvPartyclass1;
                tvPartyclass1.setSelected(true);
                break;
            case R.id.tv_partyclass2:
                viewZy.setCurrentItem(2);
            case 2:
                tv_current=tvPartyclass2;
                tvPartyclass2.setSelected(true);
                break;
        }
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyPartyActivity.this, ZhiyuanActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("我的活动");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvPartyclass0 = (TextView) findViewById(R.id.tv_partyclass0);
        tvPartyclass1 = (TextView) findViewById(R.id.tv_partyclass1);
        tvPartyclass2 = (TextView) findViewById(R.id.tv_partyclass2);
        viewZy = (ViewPager2) findViewById(R.id.view_zy);

        tv_current=tvPartyclass0;
        tvPartyclass0.setSelected(true);

        tvPartyclass0.setOnClickListener(this);
        tvPartyclass1.setOnClickListener(this);
        tvPartyclass2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }
}