package com.example.compete6.home.zhengfu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.R;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewSuqiuActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private EditText etTitle;
    private EditText etContent;
    private EditText etTake;
    private EditText etPhone;
    private Button btnFabu;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_suqiu);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL)
                .build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        Intent intent=getIntent();
        token=intent.getStringExtra("token");
        id=intent.getIntExtra("id",-1);
        initView();
        initOther();
        if(token==null){
            Toast.makeText(NewSuqiuActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
            return;
        }
        btnFabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewSuqiuActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NewSuqiuActivity.this, SuqiuListActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("新建诉求");
    }
    private void initView() {
        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        etTake = findViewById(R.id.et_take);
        etPhone = findViewById(R.id.et_phone);
        btnFabu = findViewById(R.id.btn_fabu);
    }
}