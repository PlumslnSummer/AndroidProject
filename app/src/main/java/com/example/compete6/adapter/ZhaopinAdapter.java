package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.ZhaopinBean;
import com.example.compete6.home.worktab.tab.JobDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ZhaopinAdapter extends BaseAdapter {
    private List<ZhaopinBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ZhaopinAdapter(Context context) {
        this.context = context;
    }

    public List<ZhaopinBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ZhaopinBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ZhaopinBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.job_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvJobname = (TextView) convertView.findViewById(R.id.tv_jobname);
            viewHolder.tvJobob = (TextView) convertView.findViewById(R.id.tv_jobob);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.tvSalary = (TextView) convertView.findViewById(R.id.tv_salary);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ZhaopinBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvJobname.setText("职位名称："+rowsBean.getName());
            viewHolder.tvJobob.setText("岗位职责："+rowsBean.getObligation());
            viewHolder.tvAddress.setText("公司地址："+rowsBean.getAddress());
            viewHolder.tvSalary.setText("薪资待遇："+rowsBean.getSalary());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, JobDetailActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",rowsBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvJobname;
        private TextView tvJobob;
        private TextView tvAddress;
        private TextView tvSalary;
    }

}
