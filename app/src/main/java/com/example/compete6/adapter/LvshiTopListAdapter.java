package com.example.compete6.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.compete6.R;
import com.example.compete6.bean.LvshiTopListBean;
import com.example.compete6.home.lvshi.tab.LvshiDetailActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class LvshiTopListAdapter extends BaseAdapter {
    private List<LvshiTopListBean.DataBean> dataBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LvshiTopListAdapter(Context context) {
        this.context = context;
    }

    public List<LvshiTopListBean.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<LvshiTopListBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public LvshiTopListBean.DataBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.lvshi_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivLvshi = (ImageView) convertView.findViewById(R.id.iv_lvshi);
            viewHolder.tvLvshiname = (TextView) convertView.findViewById(R.id.tv_lvshiname);
            viewHolder.tvWorktime = (TextView) convertView.findViewById(R.id.tv_worktime);
            viewHolder.tvFlzc = (TextView) convertView.findViewById(R.id.tv_flzc);
            viewHolder.tvServicestime = (TextView) convertView.findViewById(R.id.tv_servicestime);
            viewHolder.tvFavo = (TextView) convertView.findViewById(R.id.tv_favo);
            viewHolder.btnZixun = (Button) convertView.findViewById(R.id.btn_zixun);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        LvshiTopListBean.DataBean dataBean=getItem(position);
        if(dataBean!=null){
            viewHolder.tvLvshiname.setText("律师名称："+dataBean.getName());
            viewHolder.tvWorktime.setText("从业年限："+dataBean.getWorkStartAt());
            viewHolder.tvServicestime.setText("咨询人数："+dataBean.getServiceTimes());
            viewHolder.tvFlzc.setText("法律专长："+dataBean.getLegalExpertiseName());
            viewHolder.tvFavo.setText("好评率："+dataBean.getFavorableRate());
            Glide.with(context)
                    .load(Contants.WEB_URL+dataBean.getAvatarUrl())
                    .error(R.drawable.head)
                    .into(viewHolder.ivLvshi);
            viewHolder.btnZixun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, LvshiDetailActivity.class);
                    intent.putExtra("token",token);
                    intent.putExtra("id",dataBean.getId());
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }
    class ViewHolder{
        private ImageView ivLvshi;
        private TextView tvLvshiname;
        private TextView tvWorktime;
        private TextView tvFlzc;
        private TextView tvServicestime;
        private TextView tvFavo;
        private Button btnZixun;
    }

}
