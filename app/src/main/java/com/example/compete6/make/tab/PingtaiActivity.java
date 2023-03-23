package com.example.compete6.make.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;

public class PingtaiActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingtai);
        initView();
        initOther();
    }
    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PingtaiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("平台监测");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
    }
}