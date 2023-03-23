package com.example.compete6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.MovieListBean;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter2 extends RecyclerView.Adapter<MovieAdapter2.MyRecyclerViewHolder> {
    private List<MovieListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private View view;
    private OnItemClickListener onItemClickListener;


    public MovieAdapter2(Context context) {
        this.context = context;
    }

    public List<MovieListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<MovieListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        MyRecyclerViewHolder myRecyclerViewHolder = new MyRecyclerViewHolder(view);
        return myRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        holder.tvMovieTitle.setText("影片名称："+rowsBeanList.get(position).getName());
        holder.tvMoviePlaydate.setText("上映时间："+rowsBeanList.get(position).getPlayDate());
        holder.tvMovieDuration.setText("时长："+rowsBeanList.get(position).getDuration()+"分钟");
        Glide.with(context)
                .load(Contants.WEB_URL+rowsBeanList.get(position).getCover())
                .error(R.mipmap.ic_launcher)
                .into(holder.ivMovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(rowsBeanList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return rowsBeanList==null?0:rowsBeanList.size();
    }
    public interface OnItemClickListener{
        void onItemClick(MovieListBean.RowsBean rowsBean);
    }
    public void OnItemClick(OnItemClickListener onItemClickListener1){
        this.onItemClickListener=onItemClickListener1;
    }

    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMovie;
        private TextView tvMovieTitle;
        private TextView tvMoviePlaydate;
        private TextView tvMovieDuration;

        public MyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.iv_movie);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvMoviePlaydate = itemView.findViewById(R.id.tv_movie_playdate);
            tvMovieDuration = itemView.findViewById(R.id.tv_movie_duration);
        }
    }
}
