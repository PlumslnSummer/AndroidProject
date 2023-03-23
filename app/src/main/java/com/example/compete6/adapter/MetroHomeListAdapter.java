package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.compete6.R;
import com.example.compete6.bean.MetroHomeListBean;
import com.example.compete6.home.metrotab.tab.MetroDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MetroHomeListAdapter extends BaseAdapter {
    private List<MetroHomeListBean.DataBean> dataBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MetroHomeListAdapter(Context context) {
        this.context = context;
    }

    public List<MetroHomeListBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<MetroHomeListBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public MetroHomeListBean.DataBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.metro_item, null);
            viewHolder=new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvNextname = (TextView) convertView.findViewById(R.id.tv_nextname);
            viewHolder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        MetroHomeListBean.DataBean dataBean=getItem(position);
        if(dataBean!=null){
            viewHolder.tvName.setText("地铁路线名称："+dataBean.getLineName());
            viewHolder.tvNextname.setText("下一站名称："+dataBean.getNextStep().getName());
            viewHolder.tvTime.setText("到达本站时长:"+dataBean.getReachTime()+"分钟");
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MetroDetailActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",dataBean.getLineId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private TextView tvName;
        private TextView tvNextname;
        private TextView tvTime;
    }
}
