package com.example.compete6.home.aixin.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.AixintuijianBean;
import com.example.compete6.home.aixin.AixinActivity;
import com.example.compete6.util.Contants;

public class AixinDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivGongyi;
    private TextView tvName;
    private TextView tvMoneynow;
    private TextView tvMoneytotal;
    private TextView tvId;
    private TextView tvPeoplenum;
    private TextView tvJindu;
    private TextView tvTime;
    private AixintuijianBean.RowsBean rowsBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aixin_detail);

        initView();
        initOther();
        Intent intent=getIntent();
        rowsBean=(AixintuijianBean.RowsBean)intent.getSerializableExtra("gongyi");
        if(rowsBean==null){
            return;
        }
        initData();
    }

    private void initData() {
        Glide.with(this)
                .load(Contants.WEB_URL+rowsBean.getImgUrl())
                .error(R.mipmap.ic_launcher)
                .into(ivGongyi);
        tvName.setText("项目名称："+rowsBean.getName());
        tvMoneynow.setText("已募善款:"+rowsBean.getMoneyNow()+"元");
        tvMoneytotal.setText("筹款目标:"+rowsBean.getMoneyTotal()+"元");
        tvId.setText("项目备案号："+rowsBean.getId());
        tvPeoplenum.setText("参与人数:"+rowsBean.getDonateCount());
        tvJindu.setText("捐款进度："+rowsBean.getDetailsList());
        tvTime.setText("项目时间:"+rowsBean.getActivityAt());
    }
    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AixinDetailActivity.this, AixinActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("公益详情页");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivGongyi = (ImageView) findViewById(R.id.iv_gongyi);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvMoneynow = (TextView) findViewById(R.id.tv_moneynow);
        tvMoneytotal = (TextView) findViewById(R.id.tv_moneytotal);
        tvId = (TextView) findViewById(R.id.tv_id);
        tvPeoplenum = (TextView) findViewById(R.id.tv_peoplenum);
        tvJindu = (TextView) findViewById(R.id.tv_jindu);
        tvTime = (TextView) findViewById(R.id.tv_time);
    }
}