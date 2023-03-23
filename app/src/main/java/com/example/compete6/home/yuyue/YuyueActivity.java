package com.example.compete6.home.yuyue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.home.yuyue.tab.CarGunaliActivity;
import com.example.compete6.home.yuyue.tab.CarLijiActivity;
import com.example.compete6.home.yuyue.tab.MyYueyueActivity;

public class YuyueActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvCarGuanli;
    private TextView tvCarYuyue;
    private TextView tvMyYuyue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyue);
        initView();
        initOther();
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YuyueActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("预约检车");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvCarGuanli = (TextView) findViewById(R.id.tv_car_guanli);
        tvCarYuyue = (TextView) findViewById(R.id.tv_car_yuyue);
        tvCarGuanli.setOnClickListener(this);
        tvCarYuyue.setOnClickListener(this);
        tvMyYuyue = (TextView) findViewById(R.id.tv_my_yuyue);
        tvCarGuanli.setOnClickListener(this);
        tvCarYuyue.setOnClickListener(this);
        tvMyYuyue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_car_guanli:
                intent = new Intent(YuyueActivity.this, CarGunaliActivity.class);
                break;
            case R.id.tv_car_yuyue:
                intent = new Intent(YuyueActivity.this, CarLijiActivity.class);
                break;
            case R.id.tv_my_yuyue:
                intent = new Intent(YuyueActivity.this, MyYueyueActivity.class);
                break;
        }
        startActivity(intent);
    }
}