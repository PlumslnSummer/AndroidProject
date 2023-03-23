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
import com.example.compete6.bean.ZixunListBean;
import com.example.compete6.home.lvshi.tab.zixun.ZixunDetailActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class ZixunAdapter extends BaseAdapter {
    private List<ZixunListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZixunAdapter(Context context) {
        this.context = context;
    }

    public List<ZixunListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ZixunListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ZixunListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.zixun_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivLvshi = (ImageView) convertView.findViewById(R.id.iv_lvshi);
            viewHolder.tvLvshiname = (TextView) convertView.findViewById(R.id.tv_lvshiname);
            viewHolder.tvFlzc = (TextView) convertView.findViewById(R.id.tv_flzc);
            viewHolder.tvSlstate = (TextView) convertView.findViewById(R.id.tv_slstate);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ZixunListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvLvshiname.setText("律师名称："+rowsBean.getLawyerName());
            viewHolder.tvFlzc.setText("法律专长："+rowsBean.getLegalExpertiseName());
            viewHolder.tvSlstate.setText("受理状态："+rowsBean.getState());
            viewHolder.tvTime.setText("提交时间："+rowsBean.getCreateTime());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getImageUrls())
                    .error(R.drawable.head)
                    .into(viewHolder.ivLvshi);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ZixunDetailActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",rowsBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private ImageView ivLvshi;
        private TextView tvLvshiname;
        private TextView tvFlzc;
        private TextView tvSlstate;
        private TextView tvTime;
    }
    private void initView() {

    }
}
