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
import com.example.compete6.bean.RencaiBean;
import com.example.compete6.home.qingnian.RencaiDeActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class RencaiAdapter extends BaseAdapter {
    private List<RencaiBean.DataBean> dataBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RencaiAdapter(Context context) {
        this.context = context;
    }

    public List<RencaiBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<RencaiBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public RencaiBean.DataBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.rencai_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivRencai = convertView.findViewById(R.id.iv_rencai);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        RencaiBean.DataBean dataBean=getItem(position);
        if(dataBean!=null){
            viewHolder.tvName.setText(dataBean.getName());
            Glide.with(context)
                    .load(Contants.WEB_URL+dataBean.getImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivRencai);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, RencaiDeActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",dataBean.getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private ImageView ivRencai;
        private TextView tvName;
    }

}
