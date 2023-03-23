package com.example.compete6.home.zhiyuan.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.bean.ZyPartyListBean;
import com.example.compete6.home.zhiyuan.ZhiyuanActivity;

public class PartyDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvPartyTitle;
    private TextView tvPartyJianjie;
    private TextView tvPartyStart;
    private TextView tvPartyRen;
    private TextView tvPartyChenban;
    private TextView tvPartyWork;
    private ZyPartyListBean.RowsBean rowsBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_detail);
        initView();
        initOther();
        Intent intent=getIntent();
        rowsBean=(ZyPartyListBean.RowsBean)intent.getSerializableExtra("party");
        if(rowsBean==null){
            return;
        }
        initData();
    }

    private void initData() {
        tvPartyTitle.setText(rowsBean.getTitle());
        tvPartyJianjie.setText("活动简介："+rowsBean.getDetail());
        tvPartyStart.setText("活动开始时间："+rowsBean.getStartAt());
        tvPartyRen.setText("人员要求："+rowsBean.getRequireText());
        tvPartyChenban.setText("承办单位："+rowsBean.getUndertaker());
        tvPartyWork.setText("工作内容："+ rowsBean.getDetail());
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PartyDetailActivity.this, ZhiyuanPartyActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("活动详情");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvPartyTitle = (TextView) findViewById(R.id.tv_party_title);
        tvPartyJianjie = (TextView) findViewById(R.id.tv_party_jianjie);
        tvPartyStart = (TextView) findViewById(R.id.tv_party_start);
        tvPartyRen = (TextView) findViewById(R.id.tv_party_ren);
        tvPartyChenban = (TextView) findViewById(R.id.tv_party_chenban);
        tvPartyWork = (TextView) findViewById(R.id.tv_party_work);
    }
}