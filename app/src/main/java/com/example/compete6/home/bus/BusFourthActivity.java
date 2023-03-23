package com.example.compete6.home.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.bean.BusBean;

public class BusFourthActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvThirdStart;
    private TextView tvThirdEnd;
    private Button btnFourth;
    private BusBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_fourth);
        Intent intent=getIntent();
        rowsBean = (BusBean.RowsBean) intent.getSerializableExtra("bus");
        if (rowsBean == null) {
            return;
        }
        initView();
        initOther();
        initData();
    }

    private void initData() {
        tvThirdStart.setText("起点："+rowsBean.getFirst());
        tvThirdEnd.setText("终点："+rowsBean.getEnd());
        btnFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BusFourthActivity.this,BusTabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BusFourthActivity.this, BusThirdActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("第四步页面");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvThirdStart = (TextView) findViewById(R.id.tv_third_start);
        tvThirdEnd = (TextView) findViewById(R.id.tv_third_end);
        btnFourth = (Button) findViewById(R.id.btn_fourth);
    }
}