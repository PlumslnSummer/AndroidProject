package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.ChongzhiBean;

import java.util.ArrayList;
import java.util.List;

public class ChongzhiAdapter extends BaseAdapter {
    private List<ChongzhiBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public ChongzhiAdapter(Context context) {
        this.context = context;
    }

    public List<ChongzhiBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ChongzhiBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ChongzhiBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.phone_history, null);
            viewHolder=new ViewHolder();
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ChongzhiBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvPhone.setText("手机号："+rowsBean.getPhonenumber());
            viewHolder.tvTime.setText("充值时间："+rowsBean.getRechargeTime());
        }
        return convertView;
    }
    class ViewHolder{
        private TextView tvPhone;
        private TextView tvTime;
    }
}
