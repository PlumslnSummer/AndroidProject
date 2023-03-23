package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.ResultBean;
import com.example.compete6.home.laji.tab.FenleiJieshaoActivity;

import java.util.ArrayList;
import java.util.List;

public class ChangjianAdapter extends BaseAdapter {
    private List<ResultBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;


    public ChangjianAdapter(Context context) {
        this.context = context;
    }

    public List<ResultBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ResultBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public ResultBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.wuliu_item2, null);
            viewHolder=new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ResultBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getName());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FenleiJieshaoActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvName;
    }

}
