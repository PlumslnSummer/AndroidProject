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
import com.example.compete6.bean.LajiXwListBean;
import com.example.compete6.home.laji.tab.LajiNewsDeActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class LajiXinwenAdapter extends BaseAdapter {
    private List<LajiXwListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;


    public LajiXinwenAdapter(Context context) {
        this.context = context;
    }

    public List<LajiXwListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<LajiXwListBean.RowsBean> rowsBeanList) {
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
    public LajiXwListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.lajixw_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
            viewHolder.tvRiqi = convertView.findViewById(R.id.tv_riqi);
            viewHolder.ivXinwen = convertView.findViewById(R.id.iv_xinwen);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        LajiXwListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvTitle.setText("标题："+rowsBean.getTitle());
            viewHolder.tvRiqi.setText("日期："+rowsBean.getCreateTime());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivXinwen);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, LajiNewsDeActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",rowsBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        private TextView tvTitle;
        private TextView tvRiqi;
        private ImageView ivXinwen;
    }

}
