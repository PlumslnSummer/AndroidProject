package com.example.compete6.home.wuliutab.tab;

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
import com.example.compete6.bean.AddTousuBean;
import com.example.compete6.bean.SuccessBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnnTousuActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;

    private Button btnQueren;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private String token;
    private EditText etComName;
    private EditText etComNo;
    private EditText etComType;
    private EditText etComContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ann_tousu);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        initView();
        initOther();
        token = intent.getStringExtra("token");
        if (token == null) {
            Toast.makeText(AnnTousuActivity.this, "请先登录", Toast.LENGTH_LONG).show();
            return;
        }
        initData();
    }

    private void initData() {
        btnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = Integer.parseInt(etComName.getText().toString());
                String no=etComNo.getText().toString().trim();
                String type=etComType.getText().toString().trim();
                String content=etComContent.getText().toString().trim();
                AddTousuBean addTousuBean=new AddTousuBean();
                addTousuBean.setCompanyId(id);
                addTousuBean.setInfoNo(no);
                addTousuBean.setQuestionType(type);
                addTousuBean.setDescription(content);
                Call < SuccessBean > call = httpbinServices.postAddtousu(token,addTousuBean);
                call.enqueue(new Callback<SuccessBean>() {
                    @Override
                    public void onResponse(Call<SuccessBean> call, Response<SuccessBean> response) {

                        Toast.makeText(AnnTousuActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<SuccessBean> call, Throwable throwable) {

                    }
                });
            }
        });

    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnTousuActivity.this, TousuActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("新增投诉页面");
    }


    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        btnQueren = (Button) findViewById(R.id.btn_queren);
        etComName = (EditText) findViewById(R.id.et_com_name);
        etComNo = (EditText) findViewById(R.id.et_com_no);
        etComType = (EditText) findViewById(R.id.et_com_type);
        etComContent = (EditText) findViewById(R.id.et_com_content);
    }
}