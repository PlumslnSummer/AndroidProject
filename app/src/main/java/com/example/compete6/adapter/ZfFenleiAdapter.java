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
import com.example.compete6.bean.ZfFenleiBena;
import com.example.compete6.home.zhengfu.SuqiuListActivity;
import com.example.compete6.util.Contants;

import java.util.ArrayList;
import java.util.List;

public class ZfFenleiAdapter extends BaseAdapter {
    private List<ZfFenleiBena.RowsBean> rowsBeanList = new ArrayList<>();
    private Context context;
    private String token;


    public ZfFenleiAdapter(Context context) {
        this.context = context;
    }

    public List<ZfFenleiBena.RowsBean> getRowsBeanList() {
        return rowsBeanList;
    }

    public void setRowsBeanList(List<ZfFenleiBena.RowsBean> rowsBeanList) {
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
    public ZfFenleiBena.RowsBean getItem(int position) {
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
            convertView = View.inflate(context, R.layout.rencai_item, null);
            viewHolder=new ViewHolder();
            viewHolder.ivRencai = convertView.findViewById(R.id.iv_rencai);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        ZfFenleiBena.RowsBean rowsBean=getItem(position);
        if(rowsBean!=null){
            viewHolder.tvName.setText(rowsBean.getName());
            Glide.with(context)
                    .load(Contants.WEB_URL+rowsBean.getImgUrl())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.ivRencai);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SuqiuListActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("id",rowsBean.getId());
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
