package com.example.compete6.home.worktab.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.JobDetaBean;
import com.example.compete6.home.worktab.WorkTabActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvJobname;
    private TextView tvJobob;
    private TextView tvAddress;
    private TextView tvSalary;
    private TextView tvCon;
    private TextView tvMiaosu;
    private TextView tvXuqiu;
    private TextView tvCompanyname;
    private TextView tvGongsi;
    private Button btnToujianli;
    private String token;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private Integer id;
    private JobDetaBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices = retrofit.create(HttpbinServices.class);
        Intent intent = getIntent();
        initView();
        initOther();
        token = intent.getStringExtra("token");
        if (token == null) {
            Toast.makeText(JobDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
            return;
        }
        id=intent.getIntExtra("id",-1);
        initData();
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getJobDetail(token,id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    dataBean= JsonParse.getmInstance().getJob(res);
                    tvJobname.setText("职位名称："+ dataBean.getName());
                    tvJobob.setText("岗位职责："+dataBean.getObligation());
                    tvAddress.setText("公司地址："+dataBean.getAddress());
                    tvSalary.setText("薪资待遇："+dataBean.getSalary());
                    tvCon.setText("联系人："+dataBean.getContacts());
                    tvMiaosu.setText("职位描述："+dataBean.getName());
                    tvXuqiu.setText("职位需求："+dataBean.getNeed());
                    tvCompanyname.setText("公司名称："+dataBean.getCompanyName());
                    tvGongsi.setText("公司简介："+dataBean.getProfessionName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        btnToujianli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(JobDetailActivity.this,JianliActivity.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetailActivity.this, WorkTabActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("招聘详情页面");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvJobname = (TextView) findViewById(R.id.tv_jobname);
        tvJobob = (TextView) findViewById(R.id.tv_jobob);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvSalary = (TextView) findViewById(R.id.tv_salary);
        tvCon = (TextView) findViewById(R.id.tv_con);
        tvMiaosu = (TextView) findViewById(R.id.tv_miaosu);
        tvXuqiu = (TextView) findViewById(R.id.tv_xuqiu);
        tvCompanyname = (TextView) findViewById(R.id.tv_companyname);
        tvGongsi = (TextView) findViewById(R.id.tv_gongsi);
        btnToujianli = (Button) findViewById(R.id.btn_toujianli);
    }
}