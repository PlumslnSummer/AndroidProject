package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.MovieListBean;
import com.example.compete6.home.movie.MovieListDetailActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends BaseAdapter {
    private List<MovieListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public MovieAdapter(Context context) {
        this.context = context;
    }

    public List<MovieListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<MovieListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public MovieListBean.RowsBean getItem(int position) {
        return rowsBeanList == null ? null : rowsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.movie_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivMovie = (ImageView) convertView.findViewById(R.id.iv_movie);
            viewHolder.tvMovieTitle = (TextView) convertView.findViewById(R.id.tv_movie_title);
            viewHolder.tvMoviePlaydate = (TextView) convertView.findViewById(R.id.tv_movie_playdate);
            viewHolder.tvMovieDuration = (TextView) convertView.findViewById(R.id.tv_movie_duration);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        MovieListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvMovieTitle.setText("影片名称："+rowsBean.getName());
            viewHolder.tvMoviePlaydate.setText("上映时间："+rowsBean.getPlayDate());
            viewHolder.tvMovieDuration.setText("时长："+rowsBean.getDuration()+"分钟");
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getCover())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivMovie);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MovieListDetailActivity.class);
                intent.putExtra("movie",rowsBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private ImageView ivMovie;
        private TextView tvMovieTitle;
        private TextView tvMoviePlaydate;
        private TextView tvMovieDuration;
    }

}
