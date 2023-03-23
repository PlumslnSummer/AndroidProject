package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.FenleiBean;
import com.example.compete6.home.laji.tab.FenleiJieshaoActivity;

import java.util.ArrayList;
import java.util.List;

public class FenleiAdapter extends BaseAdapter {
    private List<FenleiBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;


    public FenleiAdapter(Context context) {
        this.context = context;
    }

    public List<FenleiBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<FenleiBean.RowsBean> rowsBeanList) {
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
    public FenleiBean.RowsBean getItem(int position) {
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
        FenleiBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getName());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FenleiJieshaoActivity.class);
                intent.putExtra("fenlei",rowsBean);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvName;
    }

}
