package com.example.compete6.home.moneytab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.PhoneNumAdapter;
import com.example.compete6.home.moneytab.tab.JiaofeiActivity;
import com.example.compete6.home.moneytab.tab.PhoneHistoryActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.MyListView;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoneyTabActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private RadioButton rbYd;
    private RadioButton rbDx;
    private RadioButton rbLt;
    private EditText etPhone;
    private Button btnCheck;
    private MyListView mylistviewPhone;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private ImageView ivHistory;
    private PhoneNumAdapter phoneNumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_tab);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        initView();
        initOther();
        token = intent.getStringExtra("token");
        if (token == null) {
            Toast.makeText(MoneyTabActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MoneyTabActivity.this, JiaofeiActivity.class);
                String phone=etPhone.getText().toString();
                intent.putExtra("token",token);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });
        ivHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MoneyTabActivity.this, PhoneHistoryActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoneyTabActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("生活缴费");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rbYd = (RadioButton) findViewById(R.id.rb_yd);
        rbDx = (RadioButton) findViewById(R.id.rb_dx);
        rbLt = (RadioButton) findViewById(R.id.rb_lt);
        etPhone = (EditText) findViewById(R.id.et_phone);
        btnCheck = (Button) findViewById(R.id.btn_check);
        mylistviewPhone = (MyListView) findViewById(R.id.mylistview_phone);
        ivHistory = (ImageView) findViewById(R.id.iv_history);
        phoneNumAdapter=new PhoneNumAdapter(MoneyTabActivity.this);
        mylistviewPhone.setAdapter(phoneNumAdapter);
    }
}