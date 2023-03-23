package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.ShuziListBean;
import com.example.compete6.home.shuzi.SHuziDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class SHuziAdapter extends BaseAdapter {
    private List<ShuziListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SHuziAdapter(Context context) {
        this.context = context;
    }

    public List<ShuziListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ShuziListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ShuziListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.shuzi_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvAddress = convertView.findViewById(R.id.tv_address);
            viewHolder.tvTimeShuzi = convertView.findViewById(R.id.tv_time_shuzi);
            viewHolder.tvState = convertView.findViewById(R.id.tv_state);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ShuziListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getName());
            viewHolder.tvAddress.setText("地址"+rowsBean.getAddress());
            viewHolder.tvTimeShuzi.setText("营业时间："+rowsBean.getBusinessHours());
            viewHolder.tvState.setText("状态："+rowsBean.getBusinessState());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SHuziDetailActivity.class);
                intent.putExtra("id",rowsBean.getId());
                intent.putExtra("token",token);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvTimeShuzi;
        private TextView tvState;
    }

}
