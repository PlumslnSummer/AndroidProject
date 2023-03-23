package com.example.compete6.home.bus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.BusBean;

public class SecondActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Button btnSecondnext;
    private BusBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
        btnSecondnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, BusThirdActivity.class);
                intent.putExtra("bus",rowsBean);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this, BusDetailActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("第二步页面");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        btnSecondnext = (Button) findViewById(R.id.btn_secondnext);
    }
}