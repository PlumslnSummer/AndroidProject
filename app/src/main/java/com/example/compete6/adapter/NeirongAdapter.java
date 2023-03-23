package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.NeirongListBean;
import com.example.compete6.home.qingnian.NeirRongActivity;

import java.util.ArrayList;
import java.util.List;

public class NeirongAdapter extends BaseAdapter {
    private List<NeirongListBean.DataBean> dataBeanList = new ArrayList<>();
    private Context context;
    private String token;


    public NeirongAdapter(Context context) {
        this.context = context;
    }

    public List<NeirongListBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<NeirongListBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
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
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public NeirongListBean.DataBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.neirong_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvRiqi = convertView.findViewById(R.id.tv_riqi);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        NeirongListBean.DataBean dataBean=getItem(position);
        if(dataBean!=null){
            viewHolder.tvName.setText("标题："+dataBean.getTitle());
            viewHolder.tvRiqi.setText("日期："+dataBean.getCreateTime());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NeirRongActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",dataBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvName;
        private TextView tvRiqi;
    }

}
