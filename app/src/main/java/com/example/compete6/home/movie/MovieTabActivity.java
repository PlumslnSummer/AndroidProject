package com.example.compete6.home.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.BannerAdapter;
import com.example.compete6.adapter.MovieAdapter;
import com.example.compete6.bean.BannerBean;
import com.example.compete6.bean.MovieListBean;
import com.example.compete6.home.bus.BusFourthActivity;
import com.example.compete6.home.bus.BusThirdActivity;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyListView;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieTabActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private Banner bannerMovie;
    private SearchView search;
    private MyListView mylistviewMovie;
    private TextView tvCheck;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private MovieAdapter movieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_tab);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initOther();
        initBanner();
        initData();
        tvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieTabActivity.this,MovieListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        Call<ResponseBody> call=httpbinServices.getMovieList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<MovieListBean.RowsBean> rowsBeanList=JsonParse.getmInstance().getMovieList(res);
                    movieAdapter.setRowsBeanList(rowsBeanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initBanner() {
        Call<ResponseBody> call=httpbinServices.getMovieBanner();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<BannerBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getBannerList(res);
                    bannerMovie.setImages(rowsBeanList);
                    bannerMovie.setImageLoader(new BannerAdapter());
                    bannerMovie.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
    }

    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieTabActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("看电影");
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        bannerMovie = (Banner) findViewById(R.id.banner_movie);
        search = (SearchView) findViewById(R.id.search);
        mylistviewMovie = (MyListView) findViewById(R.id.mylistview_movie);
        tvCheck = (TextView) findViewById(R.id.tv_check);
        movieAdapter=new MovieAdapter(this);
        mylistviewMovie.setAdapter(movieAdapter);
    }
}