package com.example.compete6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.ToudiListBean;

import java.util.ArrayList;
import java.util.List;

public class ToudiAdapter extends BaseAdapter {
    private List<ToudiListBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public ToudiAdapter(Context context) {
        this.context = context;
    }

    public List<ToudiListBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ToudiListBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? null : rowsBeanList.size();
    }

    @Override
    public ToudiListBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.toudi_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvJobname = (TextView) convertView.findViewById(R.id.tv_jobname);
            viewHolder.tvComname = (TextView) convertView.findViewById(R.id.tv_comname);
            viewHolder.tvSalary = (TextView) convertView.findViewById(R.id.tv_salary);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ToudiListBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvJobname.setText("岗位名称："+rowsBean.getPostName());
            viewHolder.tvComname.setText("公司名称："+rowsBean.getCompanyName());
            viewHolder.tvSalary.setText("薪资待遇："+rowsBean.getMoney());
            viewHolder.tvTime.setText("投递时间："+rowsBean.getSatrTime());
        }
        return convertView;
    }

    class ViewHolder{
        private TextView tvJobname;
        private TextView tvComname;
        private TextView tvSalary;
        private TextView tvTime;
    }

}
