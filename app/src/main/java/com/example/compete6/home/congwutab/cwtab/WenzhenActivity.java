package com.example.compete6.home.congwutab.cwtab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.FindDtBean;
import com.example.compete6.home.congwutab.CongwuActivity;
import com.example.compete6.util.Contants;

public class WenzhenActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivFind;
    private TextView tvDoctorwzName;
    private TextView tvDoctorwzZc;
    private TextView tvDtwzBh;
    private Button btnSubmit;
    private FindDtBean.RowsBean rowsBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenzhen);
        Intent intent=getIntent();
        rowsBean=(FindDtBean.RowsBean)intent.getSerializableExtra("doctor");
        if(rowsBean==null){
            return;
        }
        initView();
        initOther();
        initData();
    }

    private void initData() {
        Glide.with(this)
                .load(Contants.WEB_URL+rowsBean.getAvatar())
                .error(R.drawable.head)
                .into(ivFind);
        tvDoctorwzName.setText("医生姓名："+rowsBean.getName());
        tvDoctorwzZc.setText("医生职称："+rowsBean.getJobName());
        tvDtwzBh.setText("医生编号："+rowsBean.getPracticeNo());
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WenzhenActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(WenzhenActivity.this,CongwuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WenzhenActivity.this, FindDoctorActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("问诊页面");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivFind = (ImageView) findViewById(R.id.iv_find);
        tvDoctorwzName = (TextView) findViewById(R.id.tv_doctorwz_name);
        tvDoctorwzZc = (TextView) findViewById(R.id.tv_doctorwz_zc);
        tvDtwzBh = (TextView) findViewById(R.id.tv_dtwz_bh);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }
}