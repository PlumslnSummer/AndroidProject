package com.example.compete6.home.lvshi.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.home.lvshi.LvshiActivity;
import com.example.compete6.util.MyListView;

public class AllLvshiListActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private MyListView mylistviewAlllvshi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lvshi_list);
        initView();
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllLvshiListActivity.this, LvshiActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("律师列表");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewAlllvshi = (MyListView) findViewById(R.id.mylistview_alllvshi);
    }
}