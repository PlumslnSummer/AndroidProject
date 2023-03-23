package com.example.compete6.home.yuyue.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.home.yuyue.YuyueActivity;

public class CarGunaliActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_gunali);
        initView();
        initOther();
    }
    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CarGunaliActivity.this, YuyueActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("车辆管理");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }
}