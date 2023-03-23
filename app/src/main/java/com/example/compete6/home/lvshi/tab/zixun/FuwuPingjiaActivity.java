package com.example.compete6.home.lvshi.tab.zixun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.home.lvshi.LvshiActivity;

public class FuwuPingjiaActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuwu_pingjia);
        initView();
        initOther();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FuwuPingjiaActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FuwuPingjiaActivity.this, ZixunActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FuwuPingjiaActivity.this, ZixunActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("服务评价");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }
}