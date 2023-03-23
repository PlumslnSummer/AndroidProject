package com.example.compete6.home.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.compete6.MainActivity;
import com.example.compete6.R;
import com.example.compete6.adapter.MovieAdapter;
import com.example.compete6.adapter.MovieAdapter2;
import com.example.compete6.bean.MovieListBean;
import com.example.compete6.util.Contants;
import com.example.compete6.util.HttpbinServices;
import com.example.compete6.util.JsonParse;
import com.example.compete6.util.MyListView;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieListActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private RecyclerView mylistviewMovie;
    private Retrofit retrofit;
    private HttpbinServices httpbinServices;
    private MovieAdapter movieAdapter;
    private MovieAdapter2 movieAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        retrofit=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Contants.WEB_URL).build();
        httpbinServices=retrofit.create(HttpbinServices.class);
        initView();
        initData();
        initOther();
    }
    private void initData() {
        Call<ResponseBody> call=httpbinServices.getMovieList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String res=response.body().string().trim();
                    List<MovieListBean.RowsBean> rowsBeanList= JsonParse.getmInstance().getMovieList(res);
                    //movieAdapter.setRowsBeanList(rowsBeanList);
                    movieAdapter2.setRowsBeanList(rowsBeanList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });
        movieAdapter2.OnItemClick(new MovieAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(MovieListBean.RowsBean rowsBean) {
                Intent intent=new Intent(MovieListActivity.this, MovieListDetailActivity.class);
                intent.putExtra("movie",rowsBean);
                startActivity(intent);
            }
        });
    }
    private void initOther() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MovieListActivity.this, MovieTabActivity.class);
                startActivity(intent);
            }
        });
        tvTitle.setText("电影列表");
    }
    private void initView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        mylistviewMovie = findViewById(R.id.mylistview_movie);
       // movieAdapter=new MovieAdapter(this);
        movieAdapter2=new MovieAdapter2(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mylistviewMovie.setLayoutManager(linearLayoutManager);
      //  mylistviewMovie.setLayoutManager(new GridLayoutManager(this,2));实现网格
        mylistviewMovie.setItemAnimator(new DefaultItemAnimator());
        mylistviewMovie.setAdapter(movieAdapter2);
    }
}