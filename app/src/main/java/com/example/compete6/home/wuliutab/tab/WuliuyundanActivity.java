package com.example.compete6.home.wuliutab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.home.wuliutab.WuliuActivity;

public class WuliuyundanActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Button btnTousu;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wuliuyundan);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");

        initView();
        initOther();
        if(token==null){
            Toast.makeText(WuliuyundanActivity.this,"请先登录",Toast.LENGTH_LONG).show();
            return;
        }
        btnTousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WuliuyundanActivity.this,TousuActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WuliuyundanActivity.this, WuliuActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("物流运单详情页面");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnTousu = (Button) findViewById(R.id.btn_tousu);
    }
}