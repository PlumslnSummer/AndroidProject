package com.example.compete6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.compete6.adapter.MyFragmentAdapter;
import com.example.compete6.home.HomeBlankFragment;
import com.example.compete6.make.MakeBlankFragment;
import com.example.compete6.news.NewsBlankFragment;
import com.example.compete6.per.PerBlankFragment;
import com.example.compete6.services.ServicesBlankFragment;

import java.util.ArrayList;

/**
 * 代码为原创，归属本项目主要开发者
 * 该项目已上传github和gitee
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager2 viewpager2;
    private ProgressBar progress;
    private LinearLayout linearHome;
    private ImageView ivHome;
    private LinearLayout linearNews;
    private ImageView ivNews;
    private LinearLayout linearMake;
    private ImageView ivMake;
    private LinearLayout linearPer;
    private ImageView ivPer;
    private LinearLayout linearServices;
    private ImageView ivServices,iv_current;
    private MyFragmentAdapter myFragmentAdapter;
    private Handler handler;
    private String token=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler=new Handler();
        initView();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                progress.setVisibility(View.GONE);
//            }
//        },3000);

        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        if(token==null){
            Toast.makeText(this,"未登录",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,token,Toast.LENGTH_SHORT).show();
        }

        initPager();

        viewpager2.setOffscreenPageLimit(5);

        Intent intent1=getIntent();
        int id=intent1.getIntExtra("homeid",-1);
        switch (id){
            case 4:
                viewpager2.setCurrentItem(4);
                iv_current=ivServices;
                ivServices.setSelected(true);
                break;
        }
    }

    private void initPager() {
        ArrayList<Fragment> fragments=new ArrayList<>();
        fragments.add(HomeBlankFragment.newInstance(token,"2"));
        fragments.add(NewsBlankFragment.newInstance(token,"2"));
        fragments.add(MakeBlankFragment.newInstance(token,"2"));
        fragments.add(PerBlankFragment.newInstance(token,"2"));
        fragments.add(ServicesBlankFragment.newInstance(token,"2"));
        myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewpager2.setAdapter(myFragmentAdapter);
        viewpager2.setUserInputEnabled(false);
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
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
        iv_current.setSelected(false);
        switch (position){
            case R.id.linear_home:
                viewpager2.setCurrentItem(0);
            case 0:
                iv_current=ivHome;
                ivHome.setSelected(true);
                break;
            case R.id.linear_news:
                viewpager2.setCurrentItem(1);
            case 1:
                iv_current=ivNews;
                ivNews.setSelected(true);
                break;
            case R.id.linear_make:
                viewpager2.setCurrentItem(2);
            case 2:
                iv_current=ivMake;
                ivMake.setSelected(true);
                break;
            case R.id.linear_per:
                viewpager2.setCurrentItem(3);
            case 3:
                iv_current=ivPer;
                ivPer.setSelected(true);
                break;
            case R.id.linear_services:
                viewpager2.setCurrentItem(4);
            case 4:
                iv_current=ivServices;
                ivServices.setSelected(true);
                break;
        }
    }

    private void initView() {
        viewpager2 = (ViewPager2) findViewById(R.id.viewpager2);
//        progress = (ProgressBar) findViewById(R.id.progress);
        linearHome = (LinearLayout) findViewById(R.id.linear_home);
        ivHome = (ImageView) findViewById(R.id.iv_home);
        linearNews = (LinearLayout) findViewById(R.id.linear_news);
        ivNews = (ImageView) findViewById(R.id.iv_news);
        linearMake = (LinearLayout) findViewById(R.id.linear_make);
        ivMake = (ImageView) findViewById(R.id.iv_make);
        linearPer = (LinearLayout) findViewById(R.id.linear_per);
        ivPer = (ImageView) findViewById(R.id.iv_per);
        linearServices = (LinearLayout) findViewById(R.id.linear_services);
        ivServices = (ImageView) findViewById(R.id.iv_services);

        iv_current=ivHome;
        ivHome.setSelected(true);

        linearHome.setOnClickListener(this);
        linearNews.setOnClickListener(this);
        linearMake.setOnClickListener(this);
        linearPer.setOnClickListener(this);
        linearServices.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        changeTab(v.getId());
    }
}