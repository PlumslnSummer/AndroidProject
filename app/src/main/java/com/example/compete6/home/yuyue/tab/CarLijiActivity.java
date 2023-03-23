package com.example.compete6.home.yuyue.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.home.yuyue.YuyueActivity;

public class CarLijiActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Button btnYuyue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_liji);
        initView();
        initOther();
        btnYuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CarLijiActivity.this,"预约成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(CarLijiActivity.this,MyYueyueActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CarLijiActivity.this, YuyueActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("立即预约");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnYuyue = (Button) findViewById(R.id.btn_yuyue);
    }
}