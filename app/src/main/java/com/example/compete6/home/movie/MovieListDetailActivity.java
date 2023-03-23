package com.example.compete6.home.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.bean.MovieListBean;
import com.example.compete6.util.Contants;

public class MovieListDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivMovierDe;
    private TextView tvMovieName;
    private TextView tvMovieScore;
    private TextView tvMoviePlaydate;
    private TextView tvMovieLike;
    private ImageView ivMovieHome;
    private MovieListBean.RowsBean rowsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list_detail);
        Intent intent=getIntent();
        rowsBean=(MovieListBean.RowsBean)intent.getSerializableExtra("movie");
        if(rowsBean==null){
            return;
        }
        initView();
        initData();
        initOther();
    }

    private void initData() {
        Glide.with(this)
                .load(Contants.WEB_URL+rowsBean.getCover())
                .error(R.mipmap.ic_launcher)
                .into(ivMovierDe);
        tvMovieName.setText("影片名称："+rowsBean.getName());
        tvMovieScore.setText("评分："+rowsBean.getScore()+"分");
        tvMoviePlaydate.setText("上映时间："+rowsBean.getPlayDate());
        tvMovieLike.setText("想看人数："+rowsBean.getLikeNum()+"人");
        ivMovieHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieListDetailActivity.this, MovieTabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieListDetailActivity.this, MovieTabActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("电影详情页");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivMovierDe = (ImageView) findViewById(R.id.iv_movier_de);
        tvMovieName = (TextView) findViewById(R.id.tv_movie_name);
        tvMovieScore = (TextView) findViewById(R.id.tv_movie_score);
        tvMoviePlaydate = (TextView) findViewById(R.id.tv_movie_playdate);
        tvMovieLike = (TextView) findViewById(R.id.tv_movie_like);
        ivMovieHome = (ImageView) findViewById(R.id.iv_movie_home);
    }
}