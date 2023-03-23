package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.WuliucomBean;
import com.example.compete6.home.aixin.tab.AixinItemListActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class AixinItenAdapter extends BaseAdapter {
    private List<WuliucomBean.DataBean> dataBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AixinItenAdapter(Context context) {
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
            convertView = View.inflate(context, R.layout.wuliu_item, null);
            viewHolder=new ViewHolder();
            viewHolder.linearWuliyItem = (LinearLayout) convertView.findViewById(R.id.linear_wuliy_item);
            viewHolder.ivWuliu = (ImageView) convertView.findViewById(R.id.iv_wuliu);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        WuliucomBean.DataBean dataBean=getItem(position);
        if(dataBean!=null){
            viewHolder.tvName.setText(dataBean.getName());
            Glide.with(context)
                    .load(Contants.WEB_URL+dataBean.getImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivWuliu);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AixinItemListActivity.class);
                intent.putExtra("id",dataBean.getId());
                intent.putExtra("token",token);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private LinearLayout linearWuliyItem;
        private ImageView ivWuliu;
        private TextView tvName;
    }

}
