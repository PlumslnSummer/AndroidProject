package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.SuqiuListBean;
import com.example.compete6.home.zhengfu.NewSuqiuActivity;
import com.example.compete6.home.zhengfu.SuqiuDeActivity;

import java.util.ArrayList;
import java.util.List;

public class SuqiuListAdapter extends BaseAdapter {
    private List<SuqiuListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;


    public SuqiuListAdapter(Context context) {
        this.context = context;
    }

    public List<SuqiuListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<SuqiuListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public SuqiuListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.suqiu2_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvDanwei = convertView.findViewById(R.id.tv_danwei);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);
            viewHolder.tvState = convertView.findViewById(R.id.tv_state);
            viewHolder.tvFabu = convertView.findViewById(R.id.tv_fabu);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        SuqiuListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvTitle.setText("标题:"+rowsBean.getTitle());
            viewHolder.tvDanwei.setText("承办单位："+rowsBean.getUndertaker());
            viewHolder.tvTime.setText("提交时间："+rowsBean.getCreateTime());
            viewHolder.tvState.setText("处理状态："+rowsBean.getState());
            viewHolder.tvFabu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, NewSuqiuActivity.class);
                    intent.putExtra("token",token);
                    intent.putExtra("id",rowsBean.getId());
                    context.startActivity(intent);
                }
            });
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SuqiuDeActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",rowsBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvTitle;
        private TextView tvDanwei;
        private TextView tvTime;
        private TextView tvState;
        private TextView tvFabu;
    }

}
