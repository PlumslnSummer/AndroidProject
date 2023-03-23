package com.example.compete6.home.congwutab.cwtab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.Anlibean;
import com.example.compete6.home.congwutab.CongwuActivity;
import com.example.compete6.util.Contants;

public class AnliDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivFind;
    private TextView tvDoctorwzName;
    private TextView tvDoctorwzZc;
    private TextView tvDtwzBh;
    private TextView tvMs;
    private Button btnSubmit;
    private Anlibean.RowsBean.DoctorBean doctorBean;
    private Anlibean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anli_detail);
        Intent intent=getIntent();
        doctorBean=(Anlibean.RowsBean.DoctorBean)intent.getSerializableExtra("doctor");
        rowsBean=(Anlibean.RowsBean)intent.getSerializableExtra("anli");
        if(rowsBean==null){
            return;
        }
        initView();
        initOther();
        initData();


    }

    private void initData() {
        Glide.with(this)
                .load(Contants.WEB_URL+doctorBean.getAvatar())
                .error(R.drawable.head)
                .into(ivFind);
        tvDoctorwzName.setText("医生姓名："+doctorBean.getName());
        tvDoctorwzZc.setText("医生职称："+doctorBean.getJobName());
        tvDtwzBh.setText("执业编号："+doctorBean.getPracticeNo());
        tvMs.setText("咨询描述："+rowsBean.getQuestion());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AnliDetailActivity.this,WenzhenActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnliDetailActivity.this, CongwuActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("案例详情页面");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivFind = (ImageView) findViewById(R.id.iv_find);
        tvDoctorwzName = (TextView) findViewById(R.id.tv_doctorwz_name);
        tvDoctorwzZc = (TextView) findViewById(R.id.tv_doctorwz_zc);
        tvDtwzBh = (TextView) findViewById(R.id.tv_dtwz_bh);
        tvMs = (TextView) findViewById(R.id.tv_ms);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }
}