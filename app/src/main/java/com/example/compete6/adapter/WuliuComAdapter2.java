package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.WuliucomBean;
import com.example.compete6.home.wuliutab.tab.WuliudetailActivity;

import java.util.ArrayList;
import java.util.List;

public class WuliuComAdapter2 extends BaseAdapter {
    private List<WuliucomBean.DataBean> dataBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public WuliuComAdapter2(Context context) {
        this.context = context;
    }

    public List<WuliucomBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<WuliucomBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public WuliucomBean.DataBean getItem(int position) {
        return dataBeanList == null ? null : dataBeanList.get(position);
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
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        WuliucomBean.DataBean dataBean=getItem(position);
        if(dataBean!=null){
            viewHolder.tvName.setText(dataBean.getName());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, WuliudetailActivity.class);
                intent.putExtra("id",dataBean.getId());
                intent.putExtra("token",token);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        private TextView tvName;
    }
}