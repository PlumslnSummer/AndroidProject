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

public class BusDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvFirstStart;
    private TextView tvFirstEnd;
    private TextView tvFirstPrice;
    private TextView tvFirstMile;
    private BusBean.RowsBean rowsBean;
    private Button btnFirstnext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);
        Intent intent = getIntent();
        rowsBean = (BusBean.RowsBean) intent.getSerializableExtra("bus");
        if (rowsBean == null) {
            return;
        }
        initView();
        initData();
        iniOther();
    }

    private void iniOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusDetailActivity.this, BusTabActivity.class);

                startActivity(intent);
            }
        });
        tvTitle.setText("第一步页面");
    }

    private void initData() {
        tvFirstStart.setText("起点：" + rowsBean.getFirst());
        tvFirstEnd.setText("终点：" + rowsBean.getEnd());
        tvFirstPrice.setText("票价：" + rowsBean.getPrice()+"元");
        tvFirstMile.setText("里程：" + rowsBean.getMileage()+"km");
        btnFirstnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusDetailActivity.this, SecondActivity.class);
                intent.putExtra("bus",rowsBean);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvFirstStart = (TextView) findViewById(R.id.tv_first_start);
        tvFirstEnd = (TextView) findViewById(R.id.tv_first_end);
        tvFirstPrice = (TextView) findViewById(R.id.tv_first_price);
        tvFirstMile = (TextView) findViewById(R.id.tv_first_mile);
        btnFirstnext = (Button) findViewById(R.id.btn_firstnext);
    }
}