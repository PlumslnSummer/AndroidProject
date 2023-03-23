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
import com.example.compete6.bean.YizhanBean;
import com.example.compete6.home.qingnian.YizhanDeActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class YizhanAdapter extends BaseAdapter {
    private List<YizhanBean.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public YizhanAdapter(Context context) {
        this.context = context;
    }

    public List<YizhanBean.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<YizhanBean.RowsBean> rowsBeanList) {
        this.rowsBeanList = rowsBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rowsBeanList == null ? 0 : rowsBeanList.size();
    }

    @Override
    public YizhanBean.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.yizhan_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivYizhan = convertView.findViewById(R.id.iv_yizhan);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvChuangwei = convertView.findViewById(R.id.tv_chuangwei);
            viewHolder.tvAddress = convertView.findViewById(R.id.tv_address);
            viewHolder.tvJs = convertView.findViewById(R.id.tv_js);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        YizhanBean.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getName());
            viewHolder.tvChuangwei.setText("床位数：男"+rowsBean.getBedsCountBoy()+"个   "+"女："+rowsBean.getBedsCountGirl()+"个");
            viewHolder.tvAddress.setText("地址："+rowsBean.getAddress());
            viewHolder.tvJs.setText("介绍："+rowsBean.getIntroduce());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getCoverImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivYizhan);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, YizhanDeActivity.class);
                intent.putExtra("id",rowsBean.getId());
                intent.putExtra("token",token);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        private ImageView ivYizhan;
        private TextView tvName;
        private TextView tvChuangwei;
        private TextView tvAddress;
        private TextView tvJs;
    }
}
